package aop.advice;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 
* <p>Title: LogAdvice</p>  
* <p>
*	Description: 
*   切面（aspect） = 切入点（pointCut） + 通知（advice）
* </p> 
* @author xianxian 
* @date 2019年10月14日
 */
public class LogAdvice {
	//1.前置通知：在调用方法之前执行的代码
	//无参数
	public void logBefore()
	{
		System.out.println("日志记录...........................前置通知【调用方法之前执行的代码】，例如：权限控制.......");
	}
	//带参数
	public void logBeforeWithParams(String param)
	{
		System.out.println("日志记录...........................前置通知【调用方法之前执行的代码】，例如：权限控制.......参数：" + param);
	}
	
	//2.后置通知：在调用方法之后执行的代码
	public void logAfterReturning(boolean returnValue)
	{
		System.out.println("日志记录...........................后置通知【调用方法之后执行的代码】，例如：返回值校验.......返回值：" + returnValue);
	}
	
	//3.环绕通知：在调用方法之前，与方法调用之后需要执行的代码
	public Object logAround(ProceedingJoinPoint pjp) throws Throwable
	{
		try {
			System.out.println("日志记录............start...............环绕通知【在调用方法之前，与方法调用之后需要执行的代码】，例如：日志记录、事务管理.......调用方法的对象：" + pjp.getTarget().getClass().getName());
			System.out.println("日志记录............start...............环绕通知【在调用方法之前，与方法调用之后需要执行的代码】，例如：日志记录、事务管理.......调用方法传递的参数：" + Arrays.toString(pjp.getArgs()));
			System.out.println("日志记录............start...............环绕通知【在调用方法之前，与方法调用之后需要执行的代码】，例如：日志记录、事务管理.......调用方法的名称：" + pjp.getSignature().getName());
			
			//调用真正的方法
			Object returnValue = pjp.proceed();
			
			System.out.println("日志记录.............end..............环绕通知【在调用方法之前，与方法调用之后需要执行的代码】，例如：日志记录、事务管理.......返回值：" + returnValue);
			
			return returnValue;
		} catch (Throwable e) {
			throw e;//此时抛出异常，才可以被抛出通知接收到异常信息
		}
	}
	
	//4.抛出通知：在异常抛出之后需要执行的代码
	public void logAfterThrowing(Exception ex)
	{
		System.out.println("日志记录...........................抛出通知【在异常抛出之后需要执行的代码】，例如：日志记录、发送邮件（短信）.......异常信息：" + ex.getMessage());
	}
	
	//5.最终通知：不论代码执行是否正常 或 出现异常，最终都需要执行的代码，类似于try{}catch(){}finally{}中的finally
	public void logAfter()
	{
		System.out.println("日志记录...........................最终通知【不论代码执行是否正常 或 出现异常，最终都需要执行的代码】，例如：释放资源.......");
	}
}
