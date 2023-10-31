<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<footer class="footer mt-5 py-3 bg-light">
  		<div class="container text-center">
    		<div><span class="text-muted">© 2023 樓頂揪樓咖</span></div>
    		<div class="d-flex justify-content-end">
			<a class="" data-bs-toggle="modal" data-bs-target="#timeupModal">「付款截止時間到」模擬
			</a></div>
  		</div>

		<!-- Modal -->
		<div class="modal fade" id="timeupModal" tabindex="-1" aria-labelledby="timeupModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="timeupModalLabel">執行「付款截止時間到」模擬</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        即將模擬目前揪團中的訂單付款截止時間到的情況<br>全部的揪團中訂單都會被變更(無論原付款期限為何)<br>
		        全部的購物車都會被清空<br>
		        - 未達成團條件的訂單，狀態會變成揪團失敗<br>- 有達成團條件的訂單，狀態會變成揪團成功
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
		        <a class="btn btn-primary" href="${pageContext.request.contextPath}/GroupOrder.do?action=Timeup">確定執行</a>
		      </div>
		    </div>
		  </div>
		</div>
	</footer>