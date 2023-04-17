package com.mjz.security.config;

import com.mjz.security.custom.CustomMd5PasswordEncoder;
import com.mjz.security.filter.TokenAuthenticationFilter;
import com.mjz.security.filter.TokenLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author Lucky LanAn
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @createTime 2023/3/9-13:22
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private RedisTemplate<String, String> redisTemplate;

	@Resource
	private UserDetailsService userDetailsService;

	@Resource
	private CustomMd5PasswordEncoder customMd5PasswordEncoder;

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	/**
	 * @Description {配置 Security 信息 URI}
	 * @Date 2023/3/9 14:36
	 * @param http
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 这是配置的关键，决定哪些接口开启防护，哪些接口绕过防护
		http
				//关闭csrf跨站请求伪造
				.csrf().disable()
				// 开启跨域以便前端调用接口
				.cors().and()
				.authorizeRequests()
				// 指定某些接口不需要通过验证即可访问。登陆接口肯定是不需要认证的
				.antMatchers("/admin/system/index/login").permitAll()
				// 这里意思是其它所有接口需要认证才能访问
				.anyRequest().authenticated()
				.and()
				//TokenAuthenticationFilter放到UsernamePasswordAuthenticationFilter的前面
				//这样做就是为了除了登录的时候去查询数据库外，其他时候都用token进行认证。
				.addFilterBefore(new TokenAuthenticationFilter(redisTemplate),
						UsernamePasswordAuthenticationFilter.class)
				.addFilter(new TokenLoginFilter(authenticationManager(),redisTemplate));

		//禁用session
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 指定UserDetailService和加密器
		auth.userDetailsService(userDetailsService).passwordEncoder(customMd5PasswordEncoder);
	}

	/**
	 * @Description {配置哪些请求不拦截  排除swagger相关请求}
	 * @Date 2023/3/9 14:35
	 * @param web
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/admin/modeler/**","/diagram-viewer/**","/editor-app/**","/*.html",
				"/admin/processImage/**",
				"/admin/wechat/authorize","/admin/wechat/userInfo","/admin/wechat/bindPhone",
				"/favicon.ico","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc.html");
	}
}
