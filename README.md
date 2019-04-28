# 前言
笔者接触营销活动已经接近半年时间，围绕C端用户的邀新，留存，促活也上线了一些营销活动，为了实现组件化，在编程过程中也一直在思考如何搭建一个营销平台，
从而实现运营活动的配置化和扩展性，忙中偷闲，对半年来的营销活动工作做了一些总结和梳理，以及营销活动平台整体的业务规划，以下是这半年来的思考和沉淀。

> 如果您觉得不错有帮助，麻烦右上角点下star以示鼓励！有所不足的地方也请提下宝贵意见，一起进步~


# activity-platform 活动平台

## 项目介绍
### 功能点
1. 营销活动平台的业务规划和基础架构实现，具有良好的扩展性
2. 提供营销活动的参与，后台创建，发布等操作模板
3. 对于新活动的接入开发，只需要按照规范新加几个类，实现对应的方法即可
4. 提供规则引擎，动态支撑活动规则和优惠券规则扩展

### 技术点
- 活动平台设计开发解决方案
- 采用设计模式抽象出一套完整的活动调用流程和规则引擎
- 工程采用spring-boot框架搭建，核心bean管理通过自定义了一个Bean工厂处理，包含一个内部容器类



## 工程目录模块说明
**activity-platform** 活动平台根目录<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|—— **activity-platform-client**[module]:活动平台client包，对外提供调用方法<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|—— **activity-platform-provider**[module]:活动平台具体实现模块 <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|—— **activity-platform-document**:设计和接入开发文档 <br>


## 作者介绍
供职于杭州某创业公司，负责营销业务。喜欢总结业务技术架构，提供业务解决技术方案

## 架构图
![活动平台组件化](https://github.com/caisl/activity-platform/blob/master/activity-platform-document/src/main/document/image/%E6%B4%BB%E5%8A%A8%E5%B9%B3%E5%8F%B0%E7%BB%84%E4%BB%B6%E5%8C%96.png)

## 核心UML图
![活动平台URL图](https://github.com/caisl/activity-platform/blob/master/activity-platform-document/src/main/document/image/活动平台UML.png)


## 接入指南

1. 同步仓库到本地
<br>git clone https://github.com/caisl/activity-platform.git
2. 根据实际业务调整代码
- DB设计
activity-platform对营销活动流程框架和规则引擎提供了详细的设计和分析，DB请根据实际的需求设计，这里就不提供详细的内容。

- 新活动接入
  <br>举例：接入新人有礼活动，实现FunctionCodeEnum.ACTIVITY_PARTICIPATE：
  1. 创建请求响应对象NewCustomerPartRequest，NewCustomerPartResponse，定义属性
  2. 创建数据传输对象NewCustomerPartDTO，在实际实现过程中填充参数
  3. 创建业务处理类NewCustomerActivityParser，NewCustomerActivityHandler，填充路由注解，实现方法细节
- 新functionCode接入
  <br>举例：接入活动发布功能
  1. FunctionCodeEnum定义新的CODE：FunctionCodeEnum.ACTIVITY_RELEASE
  2. 需求分析，功能抽象，由IActivityHandler派生新的子类，定义模板方法
  3. 后续具体活动接入同新活动接入流程，创建并且实现对应的子类



## 传送门
[01业务功能设计](https://github.com/caisl/activity-platform/blob/master/activity-platform-document/src/main/document/01%E4%B8%9A%E5%8A%A1%E5%8A%9F%E8%83%BD%E8%AE%BE%E8%AE%A1.md)

[02活动平台扩展性演进](https://github.com/caisl/activity-platform/blob/master/activity-platform-document/src/main/document/02活动平台扩展性演进.md)

[03规则引擎设计](https://github.com/caisl/activity-platform/blob/master/activity-platform-document/src/main/document/03规则引擎设计.md)

[04库表设计](https://github.com/caisl/activity-platform/blob/master/activity-platform-document/src/main/document/04库表设计.md)

[05功能演示](https://github.com/caisl/activity-platform/blob/master/activity-platform-document/src/main/document/05功能演示.md)






