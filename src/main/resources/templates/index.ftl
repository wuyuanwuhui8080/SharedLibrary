<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<#include "reception/comm/script.ftl">
</head>
<body>
<#include "reception/comm/head.ftl">
<article>
    <!--banner begin-->
    <div class="banner">
        <div id="banner" class="fader">
            <li class="slide"><a href="/" target="_blank"><img src="${basePath}/blue/images/1.jpg"><span
                    class="imginfo">别让这些闹心的套路，毁了你的网页设计!</span></a>
            </li>
            <li class="slide"><a href="/" target="_blank"><img src="${basePath}/blue/images/2.jpg"><span
                    class="imginfo">网页中图片属性固定宽度，如何用js改变大小</span></a>
            </li>
            <li class="slide"><a href="/" target="_blank"><img src="${basePath}/blue/images/3.jpg"><span
                    class="imginfo">个人博客，属于我的小世界！</span></a></li>
            <div class="fader_controls">
                <div class="page prev" data-target="prev">&lsaquo;</div>
                <div class="page next" data-target="next">&rsaquo;</div>
                <ul class="pager_list">
                </ul>
            </div>
        </div>
    </div>
    <!--banner end-->
    <div class="toppic">
        <li><a href="/" target="_blank"> <i><img src="${basePath}/blue/images/1.jpg"></i>
            <h2>安静地做一个爱设计的女子</h2>
            <span>学无止境</span> </a></li>
        <li><a href="/" target="_blank"> <i><img src="${basePath}/blue/images/2.jpg"></i>
            <h2>个人博客，属于我的小世界！</h2>
            <span>学无止境</span> </a></li>
    </div>
    <main>
        <div class="news_box">
            <ul>
                <li><i><a href="/"><img src="${basePath}/blue/images/1.jpg"></a></i>
                    <h3><a href="/">html5个人博客模板《simple》</a></h3>
                </li>
                <li><i><a href="/"><img src="${basePath}/blue/images/2.jpg"></a></i>
                    <h3><a href="/">html5古典个人博客模板《青砖屋檐》</a></h3>
                </li>
                <li><i><a href="/"><img src="${basePath}/blue/images/3.jpg"></a></i>
                    <h3><a href="/">第二届 优秀个人博客模板比赛参选活动</a></h3>
                </li>
                <li><i><a href="/"><img src="${basePath}/blue/images/4.jpg"></a></i>
                    <h3><a href="/">html5个人博客模板《More》</a></h3>
                </li>
                <li><i><a href="/"><img src="${basePath}/blue/images/5.jpg"></a></i>
                    <h3><a href="/">D设计师博客-一个热爱生活的设计师</a></h3>
                </li>
                <li><i><a href="/"><img src="${basePath}/blue/images/6.jpg"></a></i>
                    <h3><a href="/">个人博客用帝国cms 自定义页面 灵动标签调用网站所有信息</a></h3>
                </li>
            </ul>
        </div>
        <div class="pics">
            <ul>
                <li><i><a href="/news/life/2018-06-17/873.html" target="_blank"><img
                        src="${basePath}/blue/images/p1.jpg"></a></i><span>安静地做一个爱设计的女子</span>
                </li>
                <li><i><a href="/news/life/2018-04-27/816.html" target="_blank"><img
                        src="${basePath}/blue/images/p2.jpg"></a></i><span>个人博客，属于我的小世界！（可以是广告）</span>
                </li>
                <li><i><a href="/jstt/bj/2015-01-09/740.html" target="_blank"><img src="${basePath}/blue/images/p3.jpg"></a></i><span>【匆匆那些年】总结个人博客经历的这四年…</span>
                </li>
            </ul>
        </div>

        <div class="blogtab">
            <ul id="blogtab">
                <li class="current">最新文章</li>
                <li><a href="list.html">心得笔记</a></li>
                <li><a href="list.html">CSS3|Html5</a></li>
                <li><a href="list.html">网站建设</a></li>
                <li><a href="list.html">文字标签</a></li>
                <li><a href="list.html">文字广告</a></li>
            </ul>
            <div class="blogs" data-scroll-reveal="enter bottom over 1s">
                <h3 class="blogtitle"><a href="/" target="_blank">别让这些闹心的套路，毁了你的网页设计!</a></h3>
                <span class="blogpic"><a href="/" title=""><img src="${basePath}/blue/images/1.jpg" alt=""></a></span>
                <p class="blogtext">如图，要实现上图效果，我采用如下方法：1、首先在数据库模型，增加字段，分别是图片2，图片3。2、增加标签模板，用if，else if
                    来判断，输出。思路已打开，样式调用就可以多样化啦！... </p>
                <div class="bloginfo">
                    <ul>
                        <li class="author"><a href="/">杨青</a></li>
                        <li class="lmname"><a href="/">学无止境</a></li>
                        <li class="timer">2018-5-13</li>
                        <li class="view"><span>34567</span>已阅读</li>
                        <li class="like">9999</li>
                    </ul>
                </div>
            </div>
            <div class="blogs" data-scroll-reveal="enter bottom over 1s">
                <h3 class="blogtitle"><a href="/" target="_blank">帝国cms 首页或者列表页 实现图文不同样式调用方法</a></h3>
                <p class="blogtext">如图，要实现上图效果，我采用如下方法：1、首先在数据库模型，增加字段，分别是图片2，图片3。2、增加标签模板，用if，else if
                    来判断，输出。思路已打开，样式调用就可以多样化啦！...</p>
                <div class="bloginfo">
                    <ul>
                        <li class="author"><a href="/">杨青</a></li>
                        <li class="lmname"><a href="/">学无止境</a></li>
                        <li class="timer">2018-5-13</li>
                        <li class="view">4567已阅读</li>
                        <li class="like">9999</li>
                    </ul>
                </div>
            </div>
            <div class="blogs" data-scroll-reveal="enter bottom over 1s">
                <h3 class="blogtitle"><a href="/" target="_blank">别让这些闹心的套路，毁了你的网页设计!</a></h3>
                <span class="bplist"><a href="/" title="">
      <li><img src="${basePath}/blue/images/2.jpg" alt=""></li>
      <li><img src="${basePath}/blue/images/3.jpg" alt=""></li>
      <li><img src="${basePath}/blue/images/4.jpg" alt=""></li>
      </a></span>
                <p class="blogtext">如图，要实现上图效果，我采用如下方法：1、首先在数据库模型，增加字段，分别是图片2，图片3。2、增加标签模板，用if，else if
                    来判断，输出。思路已打开，样式调用就可以多样化啦！... </p>
                <div class="bloginfo">
                    <ul>
                        <li class="author"><a href="/">杨青</a></li>
                        <li class="lmname"><a href="/">学无止境</a></li>
                        <li class="timer">2018-5-13</li>
                        <li class="view"><span>34567</span>已阅读</li>
                        <li class="like">9999</li>
                    </ul>
                </div>
            </div>
            <div class="blogs" data-scroll-reveal="enter bottom over 1s">
                <h3 class="blogtitle"><a href="/" target="_blank">别让这些闹心的套路，毁了你的网页设计!</a></h3>
                <span class="bigpic"><a href="/" title=""><img src="${basePath}/blue/images/5.jpg" alt=""></a></span>
                <p class="blogtext">如图，要实现上图效果，我采用如下方法：1、首先在数据库模型，增加字段，分别是图片2，图片3。2、增加标签模板，用if，else if
                    来判断，输出。思路已打开，样式调用就可以多样化啦！... </p>
                <div class="bloginfo">
                    <ul>
                        <li class="author"><a href="/">杨青</a></li>
                        <li class="lmname"><a href="/">学无止境</a></li>
                        <li class="timer">2018-5-13</li>
                        <li class="view"><span>34567</span>已阅读</li>
                        <li class="like">9999</li>
                    </ul>
                </div>
            </div>
            <div class="blogs" data-scroll-reveal="enter bottom over 1s">
                <h3 class="blogtitle"><a href="/" target="_blank">别让这些闹心的套路，毁了你的网页设计!</a></h3>
                <span class="blogpic"><a href="/" title=""><img src="images/6.jpg" alt=""></a></span>
                <p class="blogtext">如图，要实现上图效果，我采用如下方法：1、首先在数据库模型，增加字段，分别是图片2，图片3。2、增加标签模板，用if，else if
                    来判断，输出。思路已打开，样式调用就可以多样化啦！... </p>
                <div class="bloginfo">
                    <ul>
                        <li class="author"><a href="/">杨青</a></li>
                        <li class="lmname"><a href="/">学无止境</a></li>
                        <li class="timer">2018-5-13</li>
                        <li class="view"><span>34567</span>已阅读</li>
                        <li class="like">9999</li>
                    </ul>
                </div>
            </div>
            <div class="blogs" data-scroll-reveal="enter bottom over 1s">
                <h3 class="blogtitle"><a href="/" target="_blank">帝国cms 首页或者列表页 实现图文不同样式调用方法</a></h3>
                <p class="blogtext">如图，要实现上图效果，我采用如下方法：1、首先在数据库模型，增加字段，分别是图片2，图片3。2、增加标签模板，用if，else if
                    来判断，输出。思路已打开，样式调用就可以多样化啦！...</p>
                <div class="bloginfo">
                    <ul>
                        <li class="author"><a href="/">杨青</a></li>
                        <li class="lmname"><a href="/">学无止境</a></li>
                        <li class="timer">2018-5-13</li>
                        <li class="view">4567已阅读</li>
                        <li class="like">9999</li>
                    </ul>
                </div>
            </div>
            <div class="blogs" data-scroll-reveal="enter bottom over 1s">
                <h3 class="blogtitle"><a href="/" target="_blank">别让这些闹心的套路，毁了你的网页设计!</a></h3>
                <span class="bplist"><a href="/" title="">
      <li><img src="${basePath}/blue/images/7.jpg" alt=""></li>
      <li><img src="${basePath}/blue/images/8.jpg" alt=""></li>
      <li><img src="${basePath}/blue/images/9.jpg" alt=""></li>
      </a></span>
                <p class="blogtext">如图，要实现上图效果，我采用如下方法：1、首先在数据库模型，增加字段，分别是图片2，图片3。2、增加标签模板，用if，else if
                    来判断，输出。思路已打开，样式调用就可以多样化啦！... </p>
                <div class="bloginfo">
                    <ul>
                        <li class="author"><a href="/">杨青</a></li>
                        <li class="lmname"><a href="/">学无止境</a></li>
                        <li class="timer">2018-5-13</li>
                        <li class="view"><span>34567</span>已阅读</li>
                        <li class="like">9999</li>
                    </ul>
                </div>
            </div>
            <div class="blogs" data-scroll-reveal="enter bottom over 1s">
                <h3 class="blogtitle"><a href="/" target="_blank">别让这些闹心的套路，毁了你的网页设计!</a></h3>
                <span class="bigpic"><a href="/" title=""><img src="${basePath}/blue/images/10.jpg" alt=""></a></span>
                <p class="blogtext">如图，要实现上图效果，我采用如下方法：1、首先在数据库模型，增加字段，分别是图片2，图片3。2、增加标签模板，用if，else if
                    来判断，输出。思路已打开，样式调用就可以多样化啦！... </p>
                <div class="bloginfo">
                    <ul>
                        <li class="author"><a href="/">杨青</a></li>
                        <li class="lmname"><a href="/">学无止境</a></li>
                        <li class="timer">2018-5-13</li>
                        <li class="view"><span>34567</span>已阅读</li>
                        <li class="like">9999</li>
                    </ul>
                </div>
            </div>
            <div class="blogs" data-scroll-reveal="enter bottom over 1s">
                <h3 class="blogtitle"><a href="/" target="_blank">别让这些闹心的套路，毁了你的网页设计!</a></h3>
                <span class="blogpic"><a href="/" title=""><img src="images/1.jpg" alt=""></a></span>
                <p class="blogtext">如图，要实现上图效果，我采用如下方法：1、首先在数据库模型，增加字段，分别是图片2，图片3。2、增加标签模板，用if，else if
                    来判断，输出。思路已打开，样式调用就可以多样化啦！... </p>
                <div class="bloginfo">
                    <ul>
                        <li class="author"><a href="/">杨青</a></li>
                        <li class="lmname"><a href="/">学无止境</a></li>
                        <li class="timer">2018-5-13</li>
                        <li class="view"><span>34567</span>已阅读</li>
                        <li class="like">9999</li>
                    </ul>
                </div>
            </div>
            <div class="blogs" data-scroll-reveal="enter bottom over 1s">
                <h3 class="blogtitle"><a href="/" target="_blank">帝国cms 首页或者列表页 实现图文不同样式调用方法</a></h3>
                <p class="blogtext">如图，要实现上图效果，我采用如下方法：1、首先在数据库模型，增加字段，分别是图片2，图片3。2、增加标签模板，用if，else if
                    来判断，输出。思路已打开，样式调用就可以多样化啦！...</p>
                <div class="bloginfo">
                    <ul>
                        <li class="author"><a href="/">杨青</a></li>
                        <li class="lmname"><a href="/">学无止境</a></li>
                        <li class="timer">2018-5-13</li>
                        <li class="view">4567已阅读</li>
                        <li class="like">9999</li>
                    </ul>
                </div>
            </div>
            <div class="blogs" data-scroll-reveal="enter bottom over 1s">
                <h3 class="blogtitle"><a href="/" target="_blank">别让这些闹心的套路，毁了你的网页设计!</a></h3>
                <span class="bplist"><a href="/" title="">
      <li><img src="${basePath}/blue/images/2.jpg" alt=""></li>
      <li><img src="${basePath}/blue/images/3.jpg" alt=""></li>
      <li><img src="${basePath}/blue/images/4.jpg" alt=""></li>
      </a></span>
                <p class="blogtext">如图，要实现上图效果，我采用如下方法：1、首先在数据库模型，增加字段，分别是图片2，图片3。2、增加标签模板，用if，else if
                    来判断，输出。思路已打开，样式调用就可以多样化啦！... </p>
                <div class="bloginfo">
                    <ul>
                        <li class="author"><a href="/">杨青</a></li>
                        <li class="lmname"><a href="/">学无止境</a></li>
                        <li class="timer">2018-5-13</li>
                        <li class="view"><span>34567</span>已阅读</li>
                        <li class="like">9999</li>
                    </ul>
                </div>
            </div>
            <div class="blogs" data-scroll-reveal="enter bottom over 1s">
                <h3 class="blogtitle"><a href="/" target="_blank">别让这些闹心的套路，毁了你的网页设计!</a></h3>
                <span class="bigpic"><a href="/" title=""><img src="${basePath}/blue/images/5.jpg" alt=""></a></span>
                <p class="blogtext">如图，要实现上图效果，我采用如下方法：1、首先在数据库模型，增加字段，分别是图片2，图片3。2、增加标签模板，用if，else if
                    来判断，输出。思路已打开，样式调用就可以多样化啦！... </p>
                <div class="bloginfo">
                    <ul>
                        <li class="author"><a href="/">杨青</a></li>
                        <li class="lmname"><a href="/">学无止境</a></li>
                        <li class="timer">2018-5-13</li>
                        <li class="view"><span>34567</span>已阅读</li>
                        <li class="like">9999</li>
                    </ul>
                </div>
            </div>
            <div class="blogs" data-scroll-reveal="enter bottom over 1s">
                <h3 class="blogtitle"><a href="/" target="_blank">别让这些闹心的套路，毁了你的网页设计!</a></h3>
                <span class="blogpic"><a href="/" title=""><img src="${basePath}/blue/images/6.jpg" alt=""></a></span>
                <p class="blogtext">如图，要实现上图效果，我采用如下方法：1、首先在数据库模型，增加字段，分别是图片2，图片3。2、增加标签模板，用if，else if
                    来判断，输出。思路已打开，样式调用就可以多样化啦！... </p>
                <div class="bloginfo">
                    <ul>
                        <li class="author"><a href="/">杨青</a></li>
                        <li class="lmname"><a href="/">学无止境</a></li>
                        <li class="timer">2018-5-13</li>
                        <li class="view"><span>34567</span>已阅读</li>
                        <li class="like">9999</li>
                    </ul>
                </div>
            </div>
            <div class="blogs" data-scroll-reveal="enter bottom over 1s">
                <h3 class="blogtitle"><a href="/" target="_blank">帝国cms 首页或者列表页 实现图文不同样式调用方法</a></h3>
                <p class="blogtext">如图，要实现上图效果，我采用如下方法：1、首先在数据库模型，增加字段，分别是图片2，图片3。2、增加标签模板，用if，else if
                    来判断，输出。思路已打开，样式调用就可以多样化啦！...</p>
                <div class="bloginfo">
                    <ul>
                        <li class="author"><a href="/">杨青</a></li>
                        <li class="lmname"><a href="/">学无止境</a></li>
                        <li class="timer">2018-5-13</li>
                        <li class="view">4567已阅读</li>
                        <li class="like">9999</li>
                    </ul>
                </div>
            </div>
            <div class="blogs" data-scroll-reveal="enter bottom over 1s">
                <h3 class="blogtitle"><a href="/" target="_blank">别让这些闹心的套路，毁了你的网页设计!</a></h3>
                <span class="bplist"><a href="/" title="">
      <li><img src="${basePath}/blue/images/7.jpg" alt=""></li>
      <li><img src="${basePath}/blue/images/8.jpg" alt=""></li>
      <li><img src="${basePath}/blue/images/9.jpg" alt=""></li>
      </a></span>
                <p class="blogtext">如图，要实现上图效果，我采用如下方法：1、首先在数据库模型，增加字段，分别是图片2，图片3。2、增加标签模板，用if，else if
                    来判断，输出。思路已打开，样式调用就可以多样化啦！... </p>
                <div class="bloginfo">
                    <ul>
                        <li class="author"><a href="/">杨青</a></li>
                        <li class="lmname"><a href="/">学无止境</a></li>
                        <li class="timer">2018-5-13</li>
                        <li class="view"><span>34567</span>已阅读</li>
                        <li class="like">9999</li>
                    </ul>
                </div>
            </div>
            <div class="blogs" data-scroll-reveal="enter bottom over 1s">
                <h3 class="blogtitle"><a href="/" target="_blank">别让这些闹心的套路，毁了你的网页设计!</a></h3>
                <span class="bigpic"><a href="/" title=""><img src="${basePath}/blue/images/10.jpg" alt=""></a></span>
                <p class="blogtext">如图，要实现上图效果，我采用如下方法：1、首先在数据库模型，增加字段，分别是图片2，图片3。2、增加标签模板，用if，else if
                    来判断，输出。思路已打开，样式调用就可以多样化啦！... </p>
                <div class="bloginfo">
                    <ul>
                        <li class="author"><a href="/">杨青</a></li>
                        <li class="lmname"><a href="/">学无止境</a></li>
                        <li class="timer">2018-5-13</li>
                        <li class="view"><span>34567</span>已阅读</li>
                        <li class="like">9999</li>
                    </ul>
                </div>
            </div>
        </div>
    </main>
    <aside class="r_box">
        <@shiro.guest>

        </@shiro.guest>
         <@shiro.user>
          <div class="about_me">
              <h2>个人简介</h2>
              <ul>
                  <i><img style=" width:100%; height:100%;"  src="${basePath}/images/${users.headImg}"></i>
                  <p><b>姓名:</b>${users.realName}</p>
                  <p class="p_individual">
                  </p>
              </ul>
          </div>
         </@shiro.user>

        <div class="wdxc">
            <h2>图片精选</h2>
            <ul>
                <li><a href="/"><img src="${basePath}/blue/images/7.jpg"></a></li>
                <li><a href="/"><img src="${basePath}/blue/images/8.jpg"></a></li>
                <li><a href="/"><img src="${basePath}/blue/images/9.jpg"></a></li>
                <li><a href="/"><img src="${basePath}/blue/images/10.jpg"></a></li>
                <li><a href="/"><img src="${basePath}/blue/images/11.jpg"></a></li>
                <li><a href="/"><img src="${basePath}/blue/images/12.jpg"></a></li>
            </ul>
        </div>
        <div class="fenlei">
            <h2>文章分类</h2>
            <ul>
                <li><a href="/">学无止境（33）</a></li>
                <li><a href="/">日记（19）</a></li>
                <li><a href="/">慢生活（520）</a></li>
                <li><a href="/">美文欣赏（40）</a></li>
            </ul>
        </div>
        <div class="tuijian">
            <h2 id="tab"><a href="#" class="current">活动公告</a><a href="#">点击排行</a><a href="#">站长推荐</a></h2>

            <div id="content">
                <ul style="display:block;">
                    <li><a href="/">第二届 优秀个人博客模板比赛参选活动</a></li>
                    <li><a href="/">【活动作品】柠檬绿兔小白个人博客模板</a></li>
                    <li><a href="/">帝国cms 列表页调用子栏目，没有则不显示栏目名称</a></li>
                    <li><a href="/">2014年度优秀个人博客评选活动</a></li>
                    <li><a href="/">你是什么人便会遇上什么人</a></li>
                    <li><a href="/">帝国cms 列表页调用子栏目，没有则不显示栏目名称</a></li>
                    <li><a href="/">第二届 优秀个人博客模板比赛参选活动</a></li>
                    <li><a href="/">个人博客模板《绅士》后台管理</a></li>
                </ul>
                <ul>
                    <li><a href="/">你是什么人便会遇上什么人</a></li>
                    <li><a href="/">帝国cms 列表页调用子栏目，没有则不显示栏目名称</a></li>
                    <li><a href="/">第二届 优秀个人博客模板比赛参选活动</a></li>
                    <li><a href="/">个人博客模板《绅士》后台管理</a></li>
                    <li><a href="/">你是什么人便会遇上什么人</a></li>
                    <li><a href="/">帝国cms 列表页调用子栏目，没有则不显示栏目名称</a></li>
                    <li><a href="/">第二届 优秀个人博客模板比赛参选活动</a></li>
                    <li><a href="/">黑色质感时间轴html5个人博客模板</a></li>
                </ul>
                <ul>
                    <li><a href="/">个人博客，我为什么要用帝国cms？</a></li>
                    <li><a href="/">D设计师博客-一个热爱生活的设计师</a></li>
                    <li><a href="/">东轩博客，我看到了你的坚持！</a></li>
                    <li><a href="/">程序员创业，就得“豁得出去”！</a></li>
                    <li><a href="/">张建华 一个90后年轻站长！我们是对手亦是朋友！</a></li>
                    <li><a href="/">《奋斗电商》看知名微商如何做博客引流？</a></li>
                    <li><a href="/">《寻之旅》一个关于旅游，游记的个人博客</a></li>
                    <li><a href="/">【匆匆那些年】总结个人博客经历的这四年</a></li>
                </ul>
            </div>
        </div>
        <div class="guanzhu">
            <h2>关注我 么么哒</h2>
            <ul>
                <img src="${basePath}/blue/images/wx.jpg">
            </ul>
        </div>

    </aside>
</article>
<#include "reception/comm/footer.ftl">
</body>
</html>
<script>
    $(function () {
        var userIndividual = "${users.individual}";
        if (userIndividual.length > 15){
            $(".p_individual").html("<b>个人签名:</b>"+userIndividual.substring(0,15)+"....");
        }
    });
</script>
