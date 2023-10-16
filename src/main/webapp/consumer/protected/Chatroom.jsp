<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../components/head.jsp" %>
<%-- Import CSS for this page below (if any) --%>
<link rel="stylesheet"
	href="./vendor/bootstrap-5.3.1-dist/css/bootstrap.min.css">
<script src="./vendor/bootstrap-5.3.1-dist/js/bootstrap.bundle.min.js"></script>
<script src="./vendor/jquery/jquery-3.7.1.min.js"></script>
<style>
	.messages--received>.message {
		background-color: #ddd;
	}
	
	.messages--sent>.message {
		background-color: #1998e6;
		color: white;
	}
</style>


<title>樓頂揪樓咖-線上客服</title>
<%-- Remember to edit the page title --%>
</head>

<body>
	<jsp:include page="../components/nav.jsp"></jsp:include>
	<%-- The navigation bar --%>

	<%-- Page content start --%>
	<div class="container col-3">
    <div class="screen card">
      <div class="header card-header bg-primary text-light">客服</div>
      <div class="card-body p-2 conversation vh-75 overflow-auto">
        <div class="d-flex flex-column messages messages--received align-items-start">
          <div class="message rounded-pill px-3 py-1 mb-2">您好</div>
        </div>
        <div class="d-flex flex-column align-items-end messages messages--sent">
          <div class="message rounded-pill px-3 py-1 mb-2">我想請問如何註冊</div>
        </div>
        <div class="d-flex flex-column messages messages--received align-items-start">
          <div class="message rounded-pill px-3 py-1 mb-2">請於右上方找到註冊會員按鈕</div>
        </div>
        <div class="d-flex flex-column align-items-end messages messages--sent">
          <div class="message rounded-pill px-3 py-1 mb-2">好喔~謝謝</div>
        </div>
        <div class="d-flex flex-column align-items-end messages messages--sent">
          <div class="message rounded-pill px-3 py-1 mb-2">我找到了~</div>
        </div>
      </div>
      <div class="card-footer p-1">
        <div class="input-group">
          <input type="text" class="form-control" placeholder="輸入文字後 請按enter或送出">
          <div class="input-group-append px-2">
            <button class="btn btn-primary">&#10147;</button>
          </div>
        </div>
      </div>
    </div>
  </div>


	<%-- Page content end --%>

	<jsp:include page="../components/footer.jsp"></jsp:include>
	<%-- The footer --%>




	<%@ include file="../components/tail.jsp"%>
	<%-- Import JS for this page below (if any) --%>
	<script>
    $(document).ready(function () {
      function sendMessage() {
        var inputField = $('.form-control');
        var lastMessageContainer = $('.messages').last();
        var messageContent = inputField.val();

        // 創建新的訊息元素
        var newMessage = $('<div class="message rounded-pill px-3 py-1 mb-2">' + messageContent + '</div>');

        if (messageContent.trim() !== "") {
          if (lastMessageContainer.hasClass('messages--sent')) {
            lastMessageContainer.append(newMessage);
          } else {
            var newSentContainer = $('<div class="d-flex flex-column align-items-end messages messages--sent"></div>');
            newSentContainer.append(newMessage);
            lastMessageContainer.after(newSentContainer);
          }
          inputField.val('');
          $('.conversation').scrollTop($('.conversation')[0].scrollHeight);
        }
      }

      // 按下送出時
      $('.btn-primary').on('click', sendMessage);

      // 按下Enter時
      $('.form-control').on('keypress', function (e) {
        if (e.which == 13) {
          sendMessage();
        }
      });
    });
  </script>

</body>
</html>