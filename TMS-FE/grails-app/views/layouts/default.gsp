<!doctype html>
<!--[if lt IE 7]> <html class="no-js ie6 oldie" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8 oldie" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
    <head>
        <title><g:layoutTitle default="Grails" /></title>
   		
   		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    	
    	<meta name="description" content="">
		<meta name="author" content="">
	
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
    
        <link rel="stylesheet/less" href="${resource(dir:'css/libs',file:'reset.less')}" />
        <link rel="stylesheet/less" href="${resource(dir:'css',file:'layout.default.less')}" />
        
        
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        
        
        <g:javascript library="libs/jquery-1.6.2.min" />
        <g:javascript library="libs/jquery.tools.min" />
        
                       
        <g:javascript library="libs/modernizr-2.0.min" />
        <g:javascript library="libs/respond.min" />
		
		
				        
        <g:layoutHead />
        
        <g:javascript library="libs/less-1.1.3.min" />
    </head>
    <body>      
        
        <div id="header-container" class='wrapOut'>
			<header class="wrapIn">
				<img src="${resource(dir:'images',file:'talk_logo.png')}" alt="Talk" border="0" class='floatLeft' />
				<nav class='floatRight'>
					<ul>
						<li><a href="#">Some</a></li>
						<li><a href="#">navigation</a></li>
						<li><a href="#">links</a></li>
					</ul>
				</nav>
			</header>
		</div>        
        
        
        <g:layoutBody />
        
        <div id="footer-container" class='wrapOut'>
			<div class='wrapIn'>
				<footer>
					<small class='floatLeft'>&copy; Talk Management System, a Montreal based company.<br /> All rights reserved.</small>
					<span class='floatLeft blue'>Talk for iPad</span>
					<span class='floatLeft blue'>Talk for iPhone</span>
					<small class='floatLeft'>give us feeback or love</small>
					<small class='floatLeft'>follow us on twitter</small>
					<small class='floatLeft'>contact us</small>
				</footer>
			</div>
		</div>
        
        <g:javascript library="libs/jquery-1.6.2.min" />
        
    </body>
</html>