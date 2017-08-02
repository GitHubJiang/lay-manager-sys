wms.addReadyFunc(function(){

  $('.btn-search').click(function(){
      var self = $(this)
          , $th = self.closest('th')
          , $table = self.closest('table')
          , $tr = $('tr', $table.find('tbody'))
          , $sTr = $tr
          , msg = {};
      $.each($th.siblings(), function(i, th){
          var $elem = $('input', th).length != 0 ? $('input', th) : $('select', th)
              , val = $elem.val();
          if(val) {
              msg[i] = {};
              msg[i].type = $elem[0].tagName.toLocaleLowerCase();
              msg[i].value = val;
          }
      });
      if($.isEmptyObject(msg)){
          $table.next('.help-block').addClass('hidden');
          $tr.removeAttr('style');
          return false;
      }
      $.each(msg, function(i, m){
          var result = $('td:eq('+i+'):contains('+m.value+')', $sTr);
          if(m.type == 'select'){
              result = $((m.value == 1 ? 'i.text-color-success' : 'i.text-color-danger'), $sTr);
          }
          $sTr = result.closest('tr');
          $tr.hide();
          $sTr.removeAttr('style');
          if(result.length == 0){
            $table.next('.help-block').removeClass('hidden');
            return false;
          }else{
            $table.next('.help-block').addClass('hidden');
          }
      });
      return false;
  });



});