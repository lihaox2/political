/**
 * Created by li on 2015/8/3.
 */
window.onload = function(){
    var newSlideSize = function slideSize(){
    	/*width()/3--调整高度---*/
        var w = Math.ceil($(".swiper-container").width());
        $(".swiper-container,.swiper-wrapper,.swiper-slide").height(w);
    };
    newSlideSize();
    $(window).resize(function(){
        newSlideSize();
    });

    var mySwiper = new Swiper('.swiper-container',{
        pagination: '.pagination',
        autoplay:false,
        paginationClickable: true,
        onSlideChangeStart: function(){
            //回调函数
        }
    });
    
    var mySwiperCs = new Swiper('.swiper-container-cs',{
        pagination: '.pagination',
        loop:true,
        autoplay:3000,
        paginationClickable: true,
        onSlideChangeStart: function(){
            //回调函数
        }
    });
  /*  cTab("#tab1","#con1");
    cTab("#tab2","#con2");*/


};