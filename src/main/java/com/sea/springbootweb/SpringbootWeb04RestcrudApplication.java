package com.sea.springbootweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SpringBootApplication
public class SpringbootWeb04RestcrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWeb04RestcrudApplication.class, args);
	}
	@Bean
	public ViewResolver myViewResolver(){
		return new MyViewResolver();
	}
	private static class MyViewResolver implements ViewResolver{

		@Override
		public View resolveViewName(String s, Locale locale) throws Exception {
			return null;
		}
	}
	@Bean
	public Converter myformatter(){
		return new MyConverter();
	}

	private static class MyConverter implements Converter<String,Date>{

		@Override
		public Date convert(String s) {
			return null;
		}
	}
}

