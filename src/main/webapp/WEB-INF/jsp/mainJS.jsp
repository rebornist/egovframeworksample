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

    if(formData.get("hanWord") === ""){
      alert("키워드를 입력해 주세요.");
      document.getElementById("hanWord").innerText = "";
    }
    else if(hasSpecialCharacter(formData.get("hanWord"))){ // 간단한 유효성 검사
      alert("유효한 값을 입력해주세요.");
      document.getElementById("hanWord").innerText = "";
    }
    else{

      fetch(this.getAttribute("action"), {
        method: "POST",
        body: formData
      })
      .then(response => response.json())
      .then(json => {
          const responseData = json.data;
          const tbody = document.getElementById("result");
          tbody.innerHTML = "";
          for (let i = 0; i < responseData.length; i++) {
              const data = responseData[i];

              const tr = document.createElement("tr");
              const checkbox = document.createElement("input");
              checkbox.type = "checkbox";
              checkbox.name = "selectedData";
              checkbox.id = "selectedData";
              checkbox.value = (i+1);

              const numElem = document.createElement("th");
              numElem.style.width = "5%";

              const hanWordElem = document.createElement("td");
              hanWordElem.style.width = "15%";

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
              insertBtn.innerText = "등록";

              // numElem.appendChild(checkbox);
              numElem.innerText = (i+1);
              hanWordElem.innerText = data.hanWord;
              engWordElem.innerText = data.engWord;
              // engAbbrWordElem.innerText = data.engAbbrWord;
              engAbbrWordElem.appendChild(abbrInputElem);
              descriptionElem.innerText = data.description;
              inputElem.appendChild(insertBtn);

              tr.appendChild(numElem);
              tr.appendChild(hanWordElem);
              tr.appendChild(engWordElem);
              tr.appendChild(engAbbrWordElem);
              tr.appendChild(descriptionElem);
              tr.appendChild(inputElem);
              tbody.appendChild(tr);
          }
      })
      .catch(error => console.error("Error: " + error.message));
    }

  }


  function submitDictionalry(e){
    e.preventDefault();

    const target = this.parentNode.parentNode;
    const formData = new FormData();

    const cells = target.getElementsByTagName("td");

    formData.append("hanWord", cells[0].innerText);
    formData.append("engWord", cells[1].innerText);
    formData.append("engAbbrWord", cells[2].childNodes[0].value);
    formData.append("description", cells[3].innerText);

    for (var pair of formData.entries()) {
      if (hasSpecialCharacter(pair[1])) {
        alert("유효한 값을 입력해주세요.");
        return;
      }
    }

    fetch("<c:url value='/api/keyword/add.do'/>", {
      method: "POST",
      body: formData
    })
    .then(response => response.json())
    .then(json => {
      if(json.data.status === 200){
        alert("사전 등록이 완료되었습니다.");
      }
      else{
        alert("사전 등록에 실패하였습니다.");
      }
    })
    .catch(error => console.error("Error: " + error.message));
  }

  function hasSpecialCharacter(str) {
      var regex = /[!@#$%^&*()_+\-=\[\]{};':"\\|,<>\/?]/g;
      return regex.test(str);
  }

  function createInputElement() {

  }
</script>