
(function ($) {
    "use strict";
    $('.date').mask('00/00/0000');
    $('.money2').mask("#.##0,00", {reverse: true});
    var showPass = 0;
    $('.btn-show-pass').on('click', function(){
        if(showPass == 0) {
            $(this).next('input').attr('type','text');
            $(this).find('i').removeClass('fa-eye');
            $(this).find('i').addClass('fa-eye-slash');
            showPass = 1;
        }
        else {
            $(this).next('input').attr('type','password');
            $(this).find('i').removeClass('fa-eye-slash');
            $(this).find('i').addClass('fa-eye');
            showPass = 0;
        }
        
    });
    
    $('#modalExcluir').on('show.bs.modal', function(event){
    	
    	var button = $(event.relatedTarget);
    	
    	var codigoTitulo = button.data('codigo');
    	var codigoNome = button.data('descricao');
    	
    	var modal = $(this);
    	var form = modal.find('form');
    	var action = form.data('url-base');
    	
    	if(!action.endsWith('/')){
    		action += '/';
    	}
    	
    	form.attr('action', action + codigoTitulo);
    	
    	modal.find('.modal-body span').html('Tem certeza que deseja excluir  <strong>' + codigoNome + '</strong> ?')
    });

    $(function() {
    	$('[rel="tootip]').tooltip();
    	$('#currency').maskMoney({decimal: ',', thousands: '.', allowZero: true });
    })
    
    $('.custom1').owlCarousel({
        animateOut: 'slideOutDown',
        animateIn: 'flipInX',
        items:1,
        margin:30,
        stagePadding:30,
        smartSpeed:450
    });
    
    
})(jQuery);