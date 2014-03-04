package cn.daryu.shop.util;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.DeepUnwrap;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.daryu.shop.CommonAttributes;
import cn.daryu.shop.EnumConverter;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

public final class FreemarkerUtils {

	private static final ConvertUtilsBean convertUtilsBean = new FreemarkerConvertUtilsBean();

	static {
		DateConverter localDateConverter = new DateConverter();
		localDateConverter.setPatterns(CommonAttributes.DATE_PATTERNS);
		convertUtilsBean.register(localDateConverter, Date.class);
	}

	public static String process(String template, Map<String, ?> model) {
		Configuration localConfiguration = null;
		ApplicationContext localApplicationContext = SpringUtils
				.getApplicationContext();
		if (localApplicationContext != null) {
			FreeMarkerConfigurer localFreeMarkerConfigurer = (FreeMarkerConfigurer) SpringUtils
					.getBean("freeMarkerConfigurer", FreeMarkerConfigurer.class);
			if (localFreeMarkerConfigurer != null)
				localConfiguration = localFreeMarkerConfigurer
						.getConfiguration();
		}
		return process(template, model, localConfiguration);
	}

	public static String process(String template, Map<String, ?> model,
			Configuration configuration) {
		if (template == null)
			return null;
		if (configuration == null)
			configuration = new Configuration();
		StringWriter localStringWriter = new StringWriter();
		try {
			new Template("template", new StringReader(template), configuration)
					.process(model, localStringWriter);
		} catch (TemplateException localTemplateException) {
			localTemplateException.printStackTrace();
		} catch (IOException localIOException) {
			localIOException.printStackTrace();
		}
		return localStringWriter.toString();
	}

	public static <T> T getParameter(String name, Class<T> type,
			Map<String, TemplateModel> params) throws TemplateModelException {
		Assert.hasText(name);
		Assert.notNull(type);
		Assert.notNull(params);
		TemplateModel localTemplateModel = (TemplateModel) params.get(name);
		if (localTemplateModel == null)
			return null;
		Object localObject = DeepUnwrap.unwrap(localTemplateModel);
		return (T) convertUtilsBean.convert(localObject, type);
	}

	public static TemplateModel getVariable(String name, Environment env)
			throws TemplateModelException {
		Assert.hasText(name);
		Assert.notNull(env);
		return env.getVariable(name);
	}

	public static void setVariable(String name, Object value, Environment env)
			throws TemplateModelException {
		Assert.hasText(name);
		Assert.notNull(env);
		if (value instanceof TemplateModel)
			env.setVariable(name, (TemplateModel) value);
		else
			env.setVariable(name, ObjectWrapper.BEANS_WRAPPER.wrap(value));
	}

	public static void setVariables(Map<String, Object> variables,
			Environment env) throws TemplateModelException {
		Assert.notNull(variables);
		Assert.notNull(env);
		Iterator localIterator = variables.entrySet().iterator();
		while (localIterator.hasNext()) {
			Map.Entry localEntry = (Map.Entry) localIterator.next();
			String str = (String) localEntry.getKey();
			Object localObject = localEntry.getValue();
			if (localObject instanceof TemplateModel)
				env.setVariable(str, (TemplateModel) localObject);
			else
				env.setVariable(str,
						ObjectWrapper.BEANS_WRAPPER.wrap(localObject));
		}
	}

	// Bean的转化
	public static class FreemarkerConvertUtilsBean extends ConvertUtilsBean {

		public String convert(Object value) {

			if (value != null) {

				Class localClass = value.getClass();

				if ((localClass.isEnum()) && (super.lookup(localClass) == null)) {
					super.register(new EnumConverter(localClass), localClass);

				} else if ((localClass.isArray())

				&& (localClass.getComponentType().isEnum())) {
					Object localObject = null;
					if (super.lookup(localClass) == null) {

						localObject = new ArrayConverter(
								localClass,
								new EnumConverter(localClass.getComponentType()),
								0);
						((ArrayConverter) localObject)
								.setOnlyFirstToString(false);
						super.register((Converter) localObject, localClass);
					}
					localObject = super.lookup(localClass);
					return ((String) ((Converter) localObject).convert(
							String.class, value));
				}
			}
			return ((String) super.convert(value));
		}

		public Object convert(String value, Class clazz) {
			if ((clazz.isEnum()) && (super.lookup(clazz) == null))
				super.register(new EnumConverter(clazz), clazz);
			return super.convert(value, clazz);
		}

		public Object convert(String[] values, Class clazz) {
			if ((clazz.isArray()) && (clazz.getComponentType().isEnum())
					&& (super.lookup(clazz.getComponentType()) == null))
				super.register(new EnumConverter(clazz.getComponentType()),
						clazz.getComponentType());
			return super.convert(values, clazz);
		}

		public Object convert(Object value, Class targetType) {
			if (super.lookup(targetType) == null)
				if (targetType.isEnum()) {
					super.register(new EnumConverter(targetType), targetType);
				} else if ((targetType.isArray())
						&& (targetType.getComponentType().isEnum())) {
					ArrayConverter localArrayConverter = new ArrayConverter(
							targetType, new EnumConverter(
									targetType.getComponentType()), 0);
					localArrayConverter.setOnlyFirstToString(false);
					super.register(localArrayConverter, targetType);
				}
			return super.convert(value, targetType);
		}
	}

}