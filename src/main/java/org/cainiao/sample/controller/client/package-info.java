/**
 * 由【客户端应用】调用的接口<br />
 * 有用户会话<br />
 * 会根据权限注解配置 Spring Security<br />
 * 如果只有权限注解但没有权限，则这个接口必须登录，但可以没有权限<br />
 * 如果一个接口没有注解，仍然会产生会话，但用户为匿名用户<br />
 * 这样的好处是，让不需要登录的接口能识别登录用户身份和未登录用户，可以以此为依据返回不同的数据<br />
 * 如果不需要登录的接口直接被 Spring Security 忽略，那么在接口中无法分辨用户是登录了还是没登录
 */
package org.cainiao.sample.controller.client;
