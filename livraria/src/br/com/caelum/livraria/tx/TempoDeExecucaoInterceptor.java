package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Log
public class TempoDeExecucaoInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	@AroundInvoke
	public Object mostraTempoExecucao(InvocationContext context) throws Exception {
		long antes = System.currentTimeMillis();

		// execute aqui o codigo que usa o EntityManager
		Object result = context.proceed();

		long depois = System.currentTimeMillis();
		
		System.out.printf("Tempo gasto na execução do método %s da classe %s: ", this.getNomeDoMetodo(context), this.getNomeDaClasse(context));
		System.out.println(depois - antes);
		
		return result;
	}

	private String getNomeDoMetodo(InvocationContext context) {
		return context.getMethod().getName();
	}
	
	private String getNomeDaClasse(InvocationContext context) {
		
		String[] nomeComPacotes = context.getMethod().getDeclaringClass().getName().split("\\.");
		
		return nomeComPacotes[nomeComPacotes.length - 1];
	}

}
