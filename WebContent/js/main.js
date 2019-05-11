$(document).ready(function(){
	$('section#screenshots a').on('click', function(){
		$('div#modal img').attr('src', $(this).attr('data-image-url'));
	});

	var nav = $('.navbar-fixed-top');
	var distance = $('.navbar-fixed-top').offset();
	if(distance.top>=200){
		nav.addClass('effect');

	}
	$(window).scroll(function(){
		var scroll=$(window).scrollTop();
		if(scroll>=200){
			nav.addClass('effect');
		}
		else{
			nav.removeClass('effect');
		}
	});

});

// smooth scroll
$(function() {
  $('a[href*="#"]:not([href="#"])').click(function() {
    if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
      var target = $(this.hash);
      target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
      if (target.length) {
        $('html, body').animate({
          scrollTop: target.offset().top
        }, 1000);
        return false;
      }
    }
  });
});
