<html>
    <head>
        <title>Grails Forever</title>
        <meta name="layout" content="default" />
        
        <link rel="stylesheet/less" href="${resource(dir:'css/pages',file:'home.less')}" />
        
        <g:javascript>
        	$(function(){
        		
        		// main vertical scroll
				$("#main").scrollable({
				
					// basic settings
					vertical: true,
				
					// up/down keys will always control this scrollable
					keyboard: 'static',
				
					// assign left/right keys to the actively viewed scrollable
					onSeek: function(event, i) {
						horizontal.eq(i).data("scrollable").focus();

					}
				
				// main navigator (thumbnail images)
				}).navigator("#main_navi");
				
				var horizontal = $(".scrollable").scrollable({
					circular: true
				}).navigator({ 
					navi:'.navi'
				});													
				
				// when page loads setup keyboard focus on the first horzontal scrollable
				horizontal.eq(0).data("scrollable").focus();
				
				
        	});
        </g:javascript>
        
    </head>
    <body>
       
		<div class="wrapOut clearfix">
			<div class='wrapIn'>
				
				<section id='boxes' class='clearfix'>
					
					<span class="box1 floatLeft">
						<p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi</p>
					</span>
					
					<span class="box2 floatLeft">
						<h1>Sign In</h1>
						<h2>with talk.ms</h2>
						<span class='btn floatLeft'>click here</span>
					</span>
					
					<span class="box3 floatLeft">
						<h1>Register</h1>
						<h2>with talk.ms</h2>
						<span class='btn floatLeft'>click here</span>
					</span>
							
				</section>
			</div>
		</div>		
				

		<div id='scroll_navigation' class="wrapOut clearfix">
			<div class='wrapIn'>
				<section class='clearfix'>
				 	
				 		
						 	<!-- main navigator -->
							<ul id="main_navi">
							
								<li class="active">
									<h2>Flud's sexiness iphone & ipad</h2>
								</li>
								<li>
									<h2>Tips for use</h2>
								</li>
							
							</ul>
							
							<div id="main">
													
								<div id="pages">
							
									<div class="page">
										<div class="scrollable floatLeft">
											<div class="items">
												<div class="item">
													<img src="http://farm1.static.flickr.com/114/299183878_4feac12b04.jpg" style='width:792px; height:446px;' />
												</div>
												<div class="item">
													<img src="http://farm1.static.flickr.com/200/507751258_5f13e3d802.jpg" style='width:792px; height:446px;' />
							
												</div>
												<div class="item">
													<img src="http://farm1.static.flickr.com/30/37446217_14bc95631a.jpg" style='width:792px; height:446px;' />
												</div>
											</div>
										</div>
										<div class="navi"></div>
									</div>
							
									<div class="page">
										<div class="scrollable floatLeft">
											<div class="items">
												<div class="item">
													<img src="http://farm1.static.flickr.com/143/321464099_a7cfcb95cf.jpg" style='width:792px; height:446px;' />
												</div>
												<div class="item">
													<img src="http://farm1.static.flickr.com/164/399223606_b875ddf797.jpg" style='width:792px; height:446px;' />
												</div>
												<div class="item">
							
													<img src="http://farm4.static.flickr.com/3651/3445879840_7ca4b491e9.jpg" style='width:792px; height:446px;' />
												</div>
											</div>
										</div>
										<div class="navi"></div>
									</div>
								</div>
							
							</div>
					
									 	
				 	
				 	
				</section>
			</div>
		</div>
							
		
		<div class='wrapOut'>
			<div class='wrapIn'>
				<section id='features' class='clearfix'>
					<h2>Featured as the best new News App</h2>
				</section>
				
				<section id='twitter_follow' class='floatLeft box'>
					<span class='raleway uppercase floatLeft'>Talk on twitter</span><span class='btn small floatRight'>Follow Us</span>
				</section>
				
				<section id='in_action' class='floatRight box'>
					<h1>Talk in action</h1>
					<br />
					<iframe src="http://player.vimeo.com/video/7976008?title=0&amp;byline=0&amp;portrait=0" width="462" height="260" frameborder="0"></iframe>
				</section>
				
				<section id='newsletter' class='floatLeft box'>
					<span class='raleway uppercase blue'>Talk MS</span> <span class='raleway uppercase'>Newsletter</span><small class='floatRight'>*indicated required</small>
					<br />
					<form>
						<label>Email address</label>
						<input type="email" required="required">
						
						<label>Name</label>
						<input type="text" required="required">
						
					  	<input class='submit small' type="submit" value="Submit">
					  	
					</form>
				</section>
				
			</div>
		</div>
		
		
		
	
    </body>
</html>
