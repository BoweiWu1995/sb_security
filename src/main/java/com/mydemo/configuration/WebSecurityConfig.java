package com.mydemo.configuration;

import com.mydemo.handler.AuthLimitHandler;
import com.mydemo.handler.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

/**
 * @author wuwei
 * @create 2019/10/8 9:31
 * @desc
 **/
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true,jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected  void configure(HttpSecurity http) throws Exception{
        //关闭csrf
        http.csrf().disable();
        //配置登录页面
        http.formLogin().loginPage("/login").permitAll();
        //配置登录成功后的默认页面
        http.formLogin().defaultSuccessUrl("/home").successHandler(new LoginSuccessHandler());
        //登出授权
        http.logout().permitAll();
        //用户权限不足处理器
        http.exceptionHandling().accessDeniedHandler(new AuthLimitHandler());
        //授权配置
        http.authorizeRequests()
                /* 所有静态资源可以访问 */
                .antMatchers("/js/**","/css/**","/images/**").permitAll()
                /* 所有以/ad 开头的广告页面可以访问 */
                .antMatchers("/ad/**").permitAll()
                .anyRequest().fullyAuthenticated();


    }

    /*
     在内存中添加用户
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws  Exception{
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("user").password(new BCryptPasswordEncoder().encode("123456")).roles("USER")
//                .and()
//                .withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("ADMIN")
//                .and()
//                .withUser("one").password(new BCryptPasswordEncoder().encode("one")).roles("ONE")
//                .and()
//                .withUser("two").password(new BCryptPasswordEncoder().encode("two")).roles("TWO")
//                .and()
//                .withUser("three").password(new BCryptPasswordEncoder().encode("three")).roles("THREE");
//    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        return jdbcUserDetailsManager;
    }
}
