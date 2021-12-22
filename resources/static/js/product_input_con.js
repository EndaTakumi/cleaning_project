/**
 * 
 */

function conf(){
	var result = confirm("登録します。よろしいですか？");
	var element1 = document.getElementById("btn_back");
	var element2 = document.getElementById("btn_regist_move");
	 
	if(result){
		/*okが押されたら、入力確定画面に遷移*/
		element2.click();
	} else{
		/*キャンセルが押されたら、入力画面に戻る*/
		element1.click();
	}
}









