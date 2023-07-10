<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javaScript">
  document.getElementById("frm").onsubmit = function(event) {
    event.preventDefault();

    let formData = new FormData(this);

    if(formData.get("keyword") === ""){
      alert("키워드를 입력해 주세요.");
      document.getElementById("keyword").innerText = "";
    }
    else if(hasSpecialCharacter(formData.get("keyword"))){ // 간단한 이메일 유효성 검사
      alert("유효한 값을 입력해주세요.");
      document.getElementById("keyword").innerText = "";
    }
    else{
      // 그 외의 경우 폼 제출을 계속 진행
      const formDataJson = {};

      for (let [key, value] of formData.entries()) {
          formDataJson[key] = value;
      }

      fetch(this.getAttribute("action"), {
        method: "POST",
        body: formData
        // headers: {
        //   "Content-Type": "application/json",
        //   "Accept": "application/json"
        // },
        // body: JSON.stringify(formDataJson)
      })
      .then(response => response.json())
      .then(json => { console.log("Success: " + json.data) })
      .catch(error => console.error("Error: " + error.message));
    }

  }

  function hasSpecialCharacter(str) {
    var regex = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/g;
    return regex.test(str);
  }
</script>