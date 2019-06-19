        $( "#taketimeTime" ).timeDropper(
                {format:'HH:mm',
                	setCurrentTime:false,
                    }
            );
        $('#taketimeTime').css("background-color","#fff");
        
	      
        var d = new Date();
    	  if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
    		  var d =moment().format('YYYY-MM-DD');
  	          $('#taketimeDate').val(d);
	    	  $('#taketimeDate').datepicker('destroy');
	    	  $('#taketimeDate').prop('type', 'date');
	    	  //(請注意日期格式要調整成跟 JQ UI 的 datepicker 日期格式一樣)<br>
    	  }else{
    	        var d =moment().format('YYYY-MM-DD');
    	        $('#taketimeDate').val(d);
    	        $(function () {
    	        	var isValidDate = function(value, format) {
	        			format = format || false;
	        			// lets parse the date to the best of our knowledge
	        			if (format) {
	        				value = parseDate(value);
	        			}
	        			var timestamp = Date.parse(value);
	        			return isNaN(timestamp) == false;
	        	   }
	        	   var parseDate = function(value) {
	        			var m = value.match(/^(\d{1,2})(\/|-)?(\d{1,2})(\/|-)?(\d{4})$/);
	        			if (m)
	        				value = m[5] + '-' + ("00" + m[3]).slice(-2) + '-' + ("00" + m[1]).slice(-2);
	        			return value;
	        	   }
    	        	var bindDatePicker= function(){
    	        		$('#taketimeDate').datepicker({
    	            	startDate: '0',
    	                format: 'yyyy-mm-dd',
    	                endDate: '+3d',
    	                daysOfWeekDisabled: "0",
    	                disableTouchKeyboard:true,
    	                language:'zh-TW',
    	                todayBtn:'linked',
    	                clearBtn:true,
    	                orientation: "bottom auto",
    	                toggleActive: true,
    	                showOnFocus:true,
    	                todayHighlight:true,
    	                autoclose: true,
    	                allowInputToggle: true,
//    	                 defaultViewDate: { year: d.getFullYear(), month: d.getMonth(), day: d.getDate() }
    		            }).find('input:first').on("blur",function () {
    		        				// check if the date is correct. We can accept dd-mm-yyyy and yyyy-mm-dd.
    		        				// update the format if it's yyyy-mm-dd
    		        				var date = parseDate($(this).val());
    		        				if (! isValidDate(date)) {
    		        					//create date based on momentjs (we have that)
    		        					date = moment().format('YYYY-MM-DD');
    		        				}
    		        				$(this).val(date);
    		        			});
    		        	}
    	        	   bindDatePicker();
    	        	 });
	  
    	  }