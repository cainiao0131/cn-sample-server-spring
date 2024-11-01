/**
 * 系统内调用接口，由同一【系统】中的其它【服务端应用】调用<br />
 * 将当前应用配置为 Client Credential 类型的资源服务器，Filter Chain 的工作：<br />
 * <ol>
 *     <li>校验请求头中的 ClientId 与 ClientSecret</li>
 *     <li>对比请求头中的 ClientId 与当前【系统】的 ClientId，只允许来自当前【系统】的请求</li>
 * </ol>
 */
package org.cainiao.sample.controller.intrasystem;
