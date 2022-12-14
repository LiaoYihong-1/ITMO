### 1. Методологии разработки ПО. Унифицированный процесс.

Современные подходы к разработке ПО принято называть методологией（如何获取解决问题的方法）. Она наука, изучающая методы(得到瀑布模型什么的)

-Разработка требований	-Анализ

-Проектирование	-Разработка

-Тестирование		- внедрение

Эксплуатация		-вывод из эксплуатации

https://www.cnblogs.com/wj033/p/5942304.html

### 2.Требования и их категоризация. Атрибуты требований.

定义：

用户解决问题或者达到目标所需的条件或者权能

系统或者系统部件要满足合同，标准，会犯或者其他正式规定文档所需具有的条件或者权能



业务需求：

反映了组织机构或者客户对系统，产品高层次的目标要求，在文档中说明

用户需求：

文档描述了用户使用产品需要完成的任务，在Use case(使用实例)中说明

系统需求：

定义了开发人员必须实现的软件功能，以及实现系统所受到的约束。系统需求是的开发人员能完成他们的任务，从而满足了业务需求。这一部分需求在软件需求规格说明书中说明。

功能级需求：

则是基于用户级需求进一步拆分，完成功能列表的输出，即这一级需求可以导入到研发中。就是把具体的用户需求，变成软件的功能要求。 比如客户要把交通事故照片通过app发给保险公司。这是用户需求。 那么功能需求就是在这个模块下，要具有提交报险事故照片功能，上传现场照片。如果再具体下去，就是界面交互图。  现在互联网公司一提产品管理，需求设计，基本就是UX。 需求过于碎片化。

**功能性需求**（**functional requirement**）为一[软件工程](https://www.wikiwand.com/zh-sg/軟體工程)用语，功能需求定义一个软件系统或组件的[功能](https://www.wikiwand.com/zh-sg/功能_(軟體工程))，也是一个系统需提供的功能及服务[[1\]](https://www.wikiwand.com/zh-sg/功能需求#citenoteqhu1)。功能可以用一组输入、行为及输出的组合来表示。功能需求可以是计算、技术细节、资料处理或其他说明系统希望达成功能的内容。功能需求会以[非功能性需求](https://www.wikiwand.com/zh-sg/非功能性需求)（或是质量需求）为其基础，后者会描述设计或实現时的限制条件（例如性能需求、保安性或可靠度等）。

非功能性需求：

在[系统工程](https://www.wikiwand.com/zh-sg/系統工程)及[需求工程](https://www.wikiwand.com/zh-sg/需求工程)中，**非功能性需求**（**Non-functional requirement**）是指依一些条件判断系统运作情形或其特性，而不是针对系统特定行为的[需求](https://www.wikiwand.com/zh-sg/需求分析)。和非功能性需求相对的是[功能需求](https://www.wikiwand.com/zh-sg/功能需求)，后者会定义系统特定的行为或[功能](https://www.wikiwand.com/zh-sg/功能_(軟體工程))。非功能性需求也可以视为为了满足客户业务需求而需要符合，但又不在功能性需求以内的特性。

一般会在系统设计（英语：[Systems design](https://www.wikiwand.com/en/Systems_design)）中详细列出实现功能需求的计划，而会在[系统架构](https://www.wikiwand.com/zh-sg/系统架构)中详细列出实现非功能性需求的计划。一般而言，功能需求会定义系统的行为，而非功能性需求会定义系统的特性。

非功能性需求一般会称为系统的“质量”，有时也会称为“限制”、“质量属性”、“质量目标”、“质量服务需求”或“非行为性的需求”[[1\]](https://www.wikiwand.com/zh-sg/非功能性需求#citenote1)。有许多非功能性需求的英文都是以“ility”结尾，例如稳定性（stability）及可移植性（portability），因此非功能性需求有时也称为“ilities”。

非功能性需求可以分为以下的二类：

1. 执行质量（Execution qualities），可以在系统运作时观察到的质量，例如保安性及易用性等。
2. 发展质量（Evolution qualities），和软件系统结构及开发过程有关的质量，例如[软件可测试性](https://www.wikiwand.com/zh-sg/軟體可測試性)、可维护性、可扩展性、可伸缩性（scalability）等[[2\]](https://www.wikiwand.com/zh-sg/非功能性需求#citenoteWiegers032)[[3\]](https://www.wikiwand.com/zh-sg/非功能性需求#citenoteYoung013)。

作为功能性需求的补充，她通常描述了系统展现给用户的行为和执行的操作等。必须满足标准和合约。



属性：

Атрибут требования является основной составляющей «хорошего» требования. С помощью атрибутов осуществляется управление требованиями, отслеживание состояний требований, задание дополнительной информации, расширяющей текстовое содержание требования. Чтобы не перегружать формулировку требования излишними деталями, специалисты рекомендуют выносить всю дополнительную информацию в атрибуты, жестко привязанные к требованию [2]. Используя содержимое атрибутов, намного легче обрабатывать требования, осуществлять поиск, выборку и сортировку.

### 3. UML

### 4.用例

https://zhuanlan.zhihu.com/p/135348779