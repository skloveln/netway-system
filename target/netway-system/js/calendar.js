document.write("<div id=meizzCalendarLayer style='position: absolute; z-index: 9999; width:283px; height: 193px; display: none'>");
document.write("<iframe name=meizzCalendarIframe scrolling=no frameborder=0 width=100% height=100%></iframe></div>");
function writeIframe()
{
    var strIframe = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=gb2312'><style>"+
    "*{font-size: 12px; font-family: 宋体}"+
    ".bg{  color: "+ WebCalendar.lightColor +"; cursor: default; background-color: "+ WebCalendar.darkColor +";}"+
    "table#tableMain{ width: 364; height: 180;}"+
    "table#tableWeek td{ color: "+ WebCalendar.lightColor +";}"+
    "table#tableDay  td{ font-weight: bold;}"+
    "td#meizzYearHead, td#meizzYearMonth{color: "+ WebCalendar.wordColor +"}"+
    ".out { text-align: center; border-top: 1px solid "+ WebCalendar.DarkBorder +"; border-left: 1px solid "+ WebCalendar.DarkBorder +";"+
    "border-right: 1px solid "+ WebCalendar.lightColor +"; border-bottom: 1px solid "+ WebCalendar.lightColor +";}"+
    ".over{ text-align: center; border-top: 1px solid #FFFFFF; border-left: 1px solid #FFFFFF;"+
    "border-bottom: 1px solid "+ WebCalendar.DarkBorder +"; border-right: 1px solid "+ WebCalendar.DarkBorder +"}"+
    "input{ border: 1px solid "+ WebCalendar.darkColor +"; padding-top: 1px; height: 18px; cursor: pointer;"+
    "       color:"+ WebCalendar.wordColor +"; background-color: "+ WebCalendar.btnBgColor +"}"+
    "</style></head><body onselectstart='return false' style='margin: 0px' oncontextmenu='return false'><form id=meizz name=meizz>";

    if (WebCalendar.drag){ strIframe += "<script language=javascript>"+
    "var drag=false, cx=0, cy=0, o = parent.WebCalendar.calendar; document.onmousemove = function(e){e=e?e:event;"+
    "if(parent.WebCalendar.drag && drag){if(document.all&&e.button==0){drag=0;return;}if(o.style.left=='')o.style.left='0px'; if(o.style.top=='')o.style.top='0px';"+
    "o.style.left = (parseInt(o.style.left) + e.clientX-cx) + 'px';"+
    "o.style.top  = (parseInt(o.style.top)  + e.clientY-cy) + 'px';}};"+
    "document.onkeydown = function(e){e=e?e:event; switch(e.keyCode){  case 27 : parent.hiddenCalendar(); break;"+
    "case 37 : parent.prevM(); break; case 38 : parent.prevY(); break; case 39 : parent.nextM(); break; case 40 : parent.nextY(); break;"+
    "case 84 : document.getElementById('today').click(); break;} e.keyCode = 0; e.returnValue= false;};"+
    "function dragStart(e){e=e?e:event;cx=e.clientX; cy=e.clientY;drag=true;}</script>"}

    strIframe += "<select id=tmpYearSelect onblur='parent.hiddenSelect(this)' style='z-index:1;position:absolute;top:1px;left:35px;display:none;width:105;'"+
    " onchange='parent.WebCalendar.thisYear =parseInt(this.value); parent.writeCalendar();  parent.hiddenSelect(this);'></select>"+
    "<select id=tmpMonthSelect onblur='parent.hiddenSelect(this)' style='z-index:1; position:absolute;top:1px;left:145px;display:none;width:105;'"+
    " onchange='parent.WebCalendar.thisMonth=parseInt(this.value);parent.writeCalendar(); parent.hiddenSelect(this);'></select>"+

    "<table id=tableMain class=bg border=0 cellspacing=2 cellpadding=0>"+
    "<tr><td width=280 height=19 bgcolor='"+ WebCalendar.lightColor +"'>"+
    "    <table width=280 id=tableHead border=0 cellspacing=1 cellpadding=0><tr align=center>"+
    "    <td width=30 height=19 class=bg title='向前翻 1 月&#13;快捷键：←' style='cursor: pointer' onclick='parent.prevM()'><b>&lt;</b></td>"+
    "    <td width=110 id=meizzYearHead  title='点击此处选择年份' onclick='parent.funYearSelect(parseInt(this.innerHTML, 10))'"+
    "        onmouseover='this.bgColor=parent.WebCalendar.darkColor; this.style.color=parent.WebCalendar.lightColor'"+
    "        onmouseout='this.bgColor=parent.WebCalendar.lightColor; this.style.color=parent.WebCalendar.wordColor'></td>"+
    "    <td width=110 id=meizzYearMonth title='点击此处选择月份' onclick='parent.funMonthSelect(parseInt(this.innerHTML, 10))'"+
    "        onmouseover='this.bgColor=parent.WebCalendar.darkColor; this.style.color=parent.WebCalendar.lightColor'"+
    "        onmouseout='this.bgColor=parent.WebCalendar.lightColor; this.style.color=parent.WebCalendar.wordColor'></td>"+
    "    <td width=30 class=bg title='向后翻 1 月&#13;快捷键：→' onclick='parent.nextM()' style='cursor: pointer'><b>&gt;</b></td></tr></table>"+
    "</td></tr><tr><td height=20><table id=tableWeek border=1 width=280 cellpadding=0 cellspacing=0 ";
    if(WebCalendar.drag){strIframe += "onmousedown='dragStart()' onmouseup='drag=false'";}
    strIframe += " borderColorLight='"+ WebCalendar.darkColor +"' borderColorDark='"+ WebCalendar.lightColor +"'>"+
    "    <tr align=center><td height=20>日</td><td>一</td><td>二</td><td>三</td><td>四</td><td>五</td><td>六</td></tr></table>"+
    "</td></tr><tr><td valign=top width=280 bgcolor='"+ WebCalendar.lightColor +"'>"+
    "    <table id=tableDay height=120 width=280 border=0 cellspacing=1 cellpadding=0>";
         for(var x=0; x<5; x++){ strIframe += "<tr>";
         for(var y=0; y<7; y++)  strIframe += "<td class=out id='meizzDay"+ (x*7+y) +"'></td>"; strIframe += "</tr>";}
         strIframe += "<tr>";
         for(var x=35; x<39; x++) strIframe += "<td class=out id='meizzDay"+ x +"'></td>";
         strIframe +="<td colspan=3 class=out title='"+ WebCalendar.regInfo +"'><input style=' background-color: "+
         WebCalendar.btnBgColor +";cursor: pointer; padding-top: 4px; width: 100%; height: 100%; border: 0' onfocus='this.blur()'"+
         " type=button value='&nbsp; &nbsp; 关闭' onclick='parent.hiddenCalendar()'></td></tr></table>"+
    "</td></tr><tr><td height=20 width=140 bgcolor='"+ WebCalendar.lightColor +"'>"+
    "    <table border=0 cellpadding=1 cellspacing=0 width=280>"+
    "    <tr><td><input id=prevYear title='向前翻 1 年&#13;快捷键：↑' onclick='parent.prevY()' type=button value='&lt;&lt;'"+
    "    onfocus='this.blur()' style='padding:0px;meizz:expression(this.disabled=parent.WebCalendar.thisYear==1000)'><input"+
    "    onfocus='this.blur()' id=prevMonth style='padding:0px;' title='向前翻 1 月&#13;快捷键：←' onclick='parent.prevM()' type=button value='&lt;&nbsp;'>"+
    "    </td><td align=center><input id=today type=button value='Today' onfocus='this.blur()' style='padding:0px;width: 150' title='当前日期&#13;快捷键：T'"+
    "    onclick=\"parent.returnDate(new Date().getDate() +'/'+ (new Date().getMonth() +1) +'/'+ new Date().getFullYear())\">"+
    "    </td><td align=right><input style='padding:0px;' title='向后翻 1 月&#13;快捷键：→' id=nextMonth onclick='parent.nextM()' type=button value='&nbsp;&gt;'"+
    "    onfocus='this.blur()'><input id=nextYear title='向后翻 1 年&#13;快捷键：↓' onclick='parent.nextY()' type=button value='&gt;&gt;'"+
    "    onfocus='this.blur()' style='padding:0px;meizz:expression(this.disabled=parent.WebCalendar.thisYear==9999)'></td></tr></table>"+
    "</td></tr><table></form><script language='javascript'>document.getElementById('tableWeek').onmousedown=dragStart;</script></body></html>";
    
    with(WebCalendar.iframe)
    {
        document.write(strIframe); document.close();
        for(var i=0; i<39; i++)
        {
            WebCalendar.dayObj[i] = document.getElementById("meizzDay"+ i);
            WebCalendar.dayObj[i].onmouseover = dayMouseOver;
            WebCalendar.dayObj[i].onmouseout  = dayMouseOut;
            WebCalendar.dayObj[i].onclick     = returnDate;
        }
    }
}
function WebCalendarF() //初始化日历的设置
{
    this.regInfo    = "WEB Calendar \r\nAuthor: sukai\r\nDebug by 2016";
    this.daysMonth  = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
    this.day        = new Array(39);            //定义日历展示用的数组
    this.dayObj     = new Array(39);            //定义日期展示控件数组
    this.dateStyle  = null;                     //保存格式化后日期数组
    this.objExport  = null;                     //日历回传的显示控件
    this.eventSrc   = null;                     //日历显示的触发控件
    this.inputDate  = null;                     //转化外的输入的日期(d/m/yyyy)
    this.thisYear   = new Date().getFullYear(); //定义年的变量的初始值
    this.thisMonth  = new Date().getMonth()+ 1; //定义月的变量的初始值
    this.thisDay    = new Date().getDate();     //定义日的变量的初始值
    this.today      = this.thisDay +"/"+ this.thisMonth +"/"+ this.thisYear;   //今天(d/m/yyyy)
    this.iframe     = window.frames["meizzCalendarIframe"]; //日历的 iframe 载体
    this.calendar   = document.getElementById("meizzCalendarLayer");  //日历的层
    this.dateReg    = "";           //日历格式验证的正则式

    this.yearFall   = 50;           //定义年下拉框的年差值
    this.format     = "yyyy-mm-dd"; //回传日期的格式
    this.timeShow   = true;        //是否返回时间
    this.drag       = true;         //是否允许拖动
    this.darkColor  = "#CCCCCC";    //控件的暗色
    this.lightColor = "#FFFFFF";    //控件的亮色
    this.btnBgColor = "#FFFFFF";    //控件的按钮背景色
    this.wordColor  = "#000040";    //控件的文字颜色
    this.wordDark   = "#DCDCDC";    //控件的暗文字颜色
    this.dayBgColor = "#FFFFFF";    //日期数字背景色
    this.todayColor = "#FFFFFF";    //今天在日历上的标示背景色
    this.DarkBorder = "#999999";    //日期显示的立体表达色
}   var WebCalendar = new WebCalendarF();

function calendar(e) //主调函数
{
    if(!e) e = window.event.srcElement;   writeIframe();
    var o = WebCalendar.calendar.style; WebCalendar.eventSrc = e;
	if (arguments.length == 0) WebCalendar.objExport = e;
    else WebCalendar.objExport = eval(arguments[0]);

    WebCalendar.iframe.document.getElementById("tableWeek").style.cursor = WebCalendar.drag ? "move" : "default";
	var t = e.offsetTop,  h = e.offsetHeight, l = e.offsetLeft, p = e.tagName.toLowerCase();
	while (e = e.offsetParent){t += e.offsetTop; l += e.offsetLeft;}
    o.display = ""; //WebCalendar.iframe.document.body.focus();
    var cw = WebCalendar.calendar.offsetWidth, ch = WebCalendar.calendar.offsetHeight;
    var dw = document.body.offsetWidth, dl = document.body.scrollLeft, dt = document.body.scrollTop;
    
    if (document.body.offsetHeight + dt - t - h >= ch) o.top = ((p=="image")? t + h : t + h) + "px";
    else o.top  = ((t - dt < ch) ? ((p=="image")? t + h : t + h) : t - ch) + "px";
    if (dw + dl - l >= cw) o.left = l + "px"; else o.left = ((dw >= cw) ? dw - cw + dl : dl) + "px";

    if  (!WebCalendar.timeShow) WebCalendar.dateReg = /^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2})$/;
    else WebCalendar.dateReg = /^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;

    try{
        if (WebCalendar.objExport.value.trim() != ""){
            WebCalendar.dateStyle = WebCalendar.objExport.value.trim().match(WebCalendar.dateReg);
            if (WebCalendar.dateStyle == null)
            {
                WebCalendar.thisYear   = new Date().getFullYear();
                WebCalendar.thisMonth  = new Date().getMonth()+ 1;
                WebCalendar.thisDay    = new Date().getDate();
                //alert("原文本框里的日期有错误！\n可能与你定义的显示时分秒有冲突！");
                writeCalendar(); return false;
            }
            else
            {
                WebCalendar.thisYear   = parseInt(WebCalendar.dateStyle[1], 10);
                WebCalendar.thisMonth  = parseInt(WebCalendar.dateStyle[3], 10);
                WebCalendar.thisDay    = parseInt(WebCalendar.dateStyle[4], 10);
                WebCalendar.inputDate  = parseInt(WebCalendar.thisDay, 10) +"/"+ parseInt(WebCalendar.thisMonth, 10) +"/"+ 
                parseInt(WebCalendar.thisYear, 10); writeCalendar();
            }
        }  else writeCalendar();
    }  catch(e){writeCalendar();}
}
function funMonthSelect() //月份的下拉框
{
    var m = isNaN(parseInt(WebCalendar.thisMonth, 10)) ? new Date().getMonth() + 1 : parseInt(WebCalendar.thisMonth);
    var e = WebCalendar.iframe.document.getElementById("tmpMonthSelect");
    for (var i=1; i<13; i++) e.options.add(new Option(i +"月", i));
    e.style.display = ""; e.value = m; e.focus(); window.status = e.style.top;
}
function funYearSelect() //年份的下拉框
{
    var n = WebCalendar.yearFall;
    var e = WebCalendar.iframe.document.getElementById("tmpYearSelect");
    var y = isNaN(parseInt(WebCalendar.thisYear, 10)) ? new Date().getFullYear() : parseInt(WebCalendar.thisYear);
        y = (y <= 1000)? 1000 : ((y >= 9999)? 9999 : y);
    var min = (y - n >= 1000) ? y - n : 1000;
    var max = (y + n <= 9999) ? y + n : 9999;
        min = (max == 9999) ? max-n*2 : min;
        max = (min == 1000) ? min+n*2 : max;
    for (var i=min; i<=max; i++) e.options.add(new Option(i +"年", i));
    e.style.display = ""; e.value = y; e.focus();
}
function prevM()  //往前翻月份
{
    WebCalendar.thisDay = 1;
    if (WebCalendar.thisMonth==1)
    {
        WebCalendar.thisYear--;
        WebCalendar.thisMonth=13;
    }
    WebCalendar.thisMonth--; writeCalendar();
}
function nextM()  //往后翻月份
{
    WebCalendar.thisDay = 1;
    if (WebCalendar.thisMonth==12)
    {
        WebCalendar.thisYear++;
        WebCalendar.thisMonth=0;
    }
    WebCalendar.thisMonth++; writeCalendar();
}
function prevY(){WebCalendar.thisDay = 1; WebCalendar.thisYear--; writeCalendar();}//往前翻 Year
function nextY(){WebCalendar.thisDay = 1; WebCalendar.thisYear++; writeCalendar();}//往后翻 Year
function hiddenSelect(e){try{while(e.options.length)e.options.remove(e.options[0]);}catch(exp){e.innerHTML = "";} e.style.display="none";}
function hiddenCalendar(){document.getElementById("meizzCalendarLayer").style.display = "none";};
function appendZero(n){return(("00"+ n).substr(("00"+ n).length-2));}//日期自动补零程序
String.prototype.trim = function(){return this.replace(/(^\s*)|(\s*$)/g,"");}
function dayMouseOver()
{
    this.className = "over";
    this.style.backgroundColor = WebCalendar.darkColor;
    if(WebCalendar.day[this.id.substr(8)].split("/")[1] == WebCalendar.thisMonth)
    this.style.color = WebCalendar.lightColor;
}
function dayMouseOut()
{
    this.className = "out"; var d = WebCalendar.day[this.id.substr(8)], a = d.split("/");
    this.style.backgroundColor = WebCalendar.dayBgColor;
    if(a[1] == WebCalendar.thisMonth && d != WebCalendar.today)
    {
        if(WebCalendar.dateStyle && a[0] == parseInt(WebCalendar.dateStyle[4], 10))
        this.style.color = WebCalendar.lightColor;
        this.style.color = WebCalendar.wordColor;
    }
}
function writeCalendar() //对日历显示的数据的处理程序
{
    var y = parseInt(WebCalendar.thisYear);
    var m = parseInt(WebCalendar.thisMonth); 
    var d = parseInt(WebCalendar.thisDay);
    WebCalendar.daysMonth[1] = (0==y%4 && (y%100!=0 || y%400==0)) ? 29 : 28;
    if (!(y<=9999 && y >= 1000 && parseInt(m, 10)>0 && parseInt(m, 10)<13 && parseInt(d, 10)>0)){
        alert("对不起，你输入了错误的日期！");
        WebCalendar.thisYear   = new Date().getFullYear();
        WebCalendar.thisMonth  = new Date().getMonth()+ 1;
        WebCalendar.thisDay    = new Date().getDate(); }
    y = WebCalendar.thisYear;
    m = WebCalendar.thisMonth;
    d = WebCalendar.thisDay;
    WebCalendar.iframe.document.getElementById("meizzYearHead").innerHTML  = y +" 年";
    WebCalendar.iframe.document.getElementById("meizzYearMonth").innerHTML = parseInt(m, 10) +" 月";
    WebCalendar.daysMonth[1] = (0==y%4 && (y%100!=0 || y%400==0)) ? 29 : 28; //闰年二月为29天
    var w = new Date(y, m-1, 1).getDay();
    var prevDays = m==1  ? WebCalendar.daysMonth[11] : WebCalendar.daysMonth[m-2];
    for(var i=(w-1); i>=0; i--) //这三个 for 循环为日历赋数据源（数组 WebCalendar.day）格式是 d/m/yyyy
    {
        WebCalendar.day[i] = prevDays +"/"+ (parseInt(m, 10)-1) +"/"+ y;
        if(m==1) WebCalendar.day[i] = prevDays +"/"+ 12 +"/"+ (parseInt(y, 10)-1);
        prevDays--;
    }
    for(var i=1; i<=WebCalendar.daysMonth[m-1]; i++) WebCalendar.day[i+w-1] = i +"/"+ m +"/"+ y;
    for(var i=1; i<39-w-WebCalendar.daysMonth[m-1]+1; i++)
    {
        WebCalendar.day[WebCalendar.daysMonth[m-1]+w-1+i] = i +"/"+ (parseInt(m, 10)+1) +"/"+ y;
        if(m==12) WebCalendar.day[WebCalendar.daysMonth[m-1]+w-1+i] = i +"/"+ 1 +"/"+ (parseInt(y, 10)+1);
    }
    for(var i=0; i<39; i++)    //这个循环是根据源数组写到日历里显示
    {
        var a = WebCalendar.day[i].split("/");
        WebCalendar.dayObj[i].innerHTML    = a[0];
        WebCalendar.dayObj[i].title        = a[2] +"-"+ appendZero(a[1]) +"-"+ appendZero(a[0]);
        WebCalendar.dayObj[i].bgColor      = WebCalendar.dayBgColor;
        WebCalendar.dayObj[i].style.color  = WebCalendar.wordColor;
        if ((i<10 && parseInt(WebCalendar.day[i], 10)>20) || (i>27 && parseInt(WebCalendar.day[i], 10)<12))
            WebCalendar.dayObj[i].style.color = WebCalendar.wordDark;
        if (WebCalendar.inputDate==WebCalendar.day[i])    //设置输入框里的日期在日历上的颜色
        {WebCalendar.dayObj[i].bgColor = WebCalendar.darkColor; WebCalendar.dayObj[i].style.color = WebCalendar.lightColor;}
        if (WebCalendar.day[i] == WebCalendar.today)      //设置今天在日历上反应出来的颜色
        {WebCalendar.dayObj[i].bgColor = WebCalendar.todayColor; WebCalendar.dayObj[i].style.color = WebCalendar.lightColor;}
    }
}
function returnDate(e) //根据日期格式等返回用户选定的日期
{
    if(WebCalendar.objExport)
    {
        var returnValue;
        var a = (typeof(e) == "undefined" || typeof(e.split) == "undefined") ? WebCalendar.day[parseInt(this.id.substr(8, this.id.length - 8))].split("/") : e.toString().split("/");
        var d = WebCalendar.format.match(/^(\w{4})(-|\/|.|)(\w{1,2})\2(\w{1,2})$/);
        if(d==null){alert("你设定的日期输出格式不对！\r\n\r\n请重新定义 WebCalendar.format ！"); return false;}
        var flag = d[3].length==2 || d[4].length==2; //判断返回的日期格式是否要补零
        returnValue = flag ? a[2] +d[2]+ appendZero(a[1]) +d[2]+ appendZero(a[0]) : a[2] +d[2]+ a[1] +d[2]+ a[0];
        if(WebCalendar.timeShow)
        {
            var h = new Date().getHours(), m = new Date().getMinutes(), s = new Date().getSeconds();
            returnValue += flag ? " "+ appendZero(h) +":"+ appendZero(m) +":"+ appendZero(s) : " "+  h  +":"+ m +":"+ s;
        }
        WebCalendar.objExport.value = returnValue;
        hiddenCalendar();
    }
}
document.onclick = function(e)
{
    var nowLabel = window.event ? window.event.srcElement : e.target;
    if(WebCalendar.eventSrc != nowLabel) hiddenCalendar();
}