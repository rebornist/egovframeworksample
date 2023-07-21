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
      alert("<spring:message code='button.create' />");
      document.getElementById("keyword").innerText = "";
    }
    else if(hasSpecialCharacter(formData.get("keyword"))){
      alert("<spring:message code='button.create' />");
      document.getElementById("keyword").innerText = "";
    }
    else{

      fetch(this.getAttribute("action"), {
        method: "POST",
        body: formData
      })
      .then(response => response.json())
      .then(json => {
        if (json.status === 200) {
          const responseData = json.data;
          const tbody = document.getElementById("result");
          tbody.innerHTML = "";
          for (let i = 0; i < responseData.length; i++) {
            const data = responseData[i];

            const tr = document.createElement("tr");
            const checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkbox.id = "search-result";
            checkbox.value = (i+1);

            const numElem = document.createElement("th");
            numElem.style.width = "5%";

            const keywordElem = document.createElement("td");
            keywordElem.style.width = "15%";

            const engWordElem = document.createElement("td");
            engWordElem.style.width = "15%";

            const engAbbrWordElem = document.createElement("td");
            engAbbrWordElem.style.width = "10%";
            engAbbrWordElem.id = "abbrInputParent";

            const abbrInputElem = document.createElement("input");
            abbrInputElem.type = "text";
            abbrInputElem.name = "abbrInput";
            abbrInputElem.id = "abbrInput";
            abbrInputElem.className = "form-control form-control-sm border border-info";

            const descriptionElem = document.createElement("td");
            descriptionElem.style.width = "50%";
            descriptionElem.className = "text-sm-start";

            const inputElem = document.createElement("td");
            inputElem.style.width = "5%";

            const insertBtn = document.createElement("button");
            insertBtn.type = "button";
            insertBtn.className = "btn btn-outline-success btn-sm";
            insertBtn.onclick = submitDictionalry;
            insertBtn.innerText = "<spring:message code='button.create' />";

            numElem.innerText = (i+1);
            keywordElem.innerText = data.keyword;
            engWordElem.innerText = data.engWord;
            engAbbrWordElem.appendChild(abbrInputElem);
            descriptionElem.innerText = data.description;
            inputElem.appendChild(insertBtn);

            tr.appendChild(numElem);
            tr.appendChild(keywordElem);
            tr.appendChild(engWordElem);
            tr.appendChild(engAbbrWordElem);
            tr.appendChild(descriptionElem);
            tr.appendChild(inputElem);
            tbody.appendChild(tr);
          }
        } else {
          alert(json.message);
        }
      })
      .catch(error => alert("<spring:message code="fail.request.msg" />"));
    }

  }


  function submitDictionalry(e){
    e.preventDefault();

    const target = this.parentNode.parentNode;
    const formData = new FormData();

    const cells = target.getElementsByTagName("td");

    formData.append("keyword", cells[0].innerText);
    formData.append("engWord", cells[1].innerText);
    formData.append("engAbbrWord", cells[2].childNodes[0].value);
    formData.append("description", cells[3].innerText);

    for (var pair of formData.entries()) {
      if (hasSpecialCharacter(pair[1])) {
        alert("<spring:message code="fail.request.msg" />");
        return;
      }
    }

    fetch("<c:url value='/api/keyword/add.do'/>", {
      method: "POST",
      body: formData
    })
    .then(response => response.json())
    .then(json => {
      if(json.status === 200){
        alert("사전 등록이 완료되었습니다.");
      }
      else{
        alert("사전 등록에 실패하였습니다.");
      }
    })
    .catch(error => alert("<spring:message code="fail.request.msg" />"));
  }

  function hasSpecialCharacter(str) {
    var regex = /[!@#$%^&*()_+\-=\[\]{};':"\\|.<>\/?]/g;
    return regex.test(str);
  }
</script>