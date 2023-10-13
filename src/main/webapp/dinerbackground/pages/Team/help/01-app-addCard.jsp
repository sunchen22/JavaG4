<%@ page contentType="text/html; charset=Big5" pageEncoding="Big5" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- 測試用的css -->
    <style>
      section.content {
        background-color: aqua;
      }

      div.card-row {
        background-color: burlywood;

      }
    </style>
  </head>

  <body>
    <%! int count=0; %>
      <section class="content pb-3">
        <% count++ %>
          <div class="container-fluid h-100">
            <button id="btnCreateCard">按我產生新卡片</button>
            <div class="card card-row card-secondary" id="applyAD"></div>
          </div>

      </section>
      <script>

        // 廣告上架區間控制
        // 廣告天數自動跳轉

        // 顯現卡片抬頭
        // class="cardName"

        // 管理卡片號碼

        // 創造卡片
        document.getElementById('btnCreateCard').addEventListener('click', function () {
          //加入html內容
          let createCard = `
            <div class="card-header">
              <h3 class="card-title">
                提出申請
              </h3>
            </div>
            <!-- =======提出申請 內容==== -->
            <div class="card-body">
              <!-- 卡片1 -->
              <div class="card card-info card-outline">
                <!-- 卡片1 表頭 -->
                <div class="card-header">
                  <h3 class="card-title">項目</h3>
                  <!-- 卡片1右側縮小、刪除鍵 -->
                  <div class="card-tools">
                    <button type="button" class="btn btn-tool" data-card-widget="collapse">
                      <i class="fas fa-minus"></i>
                    </button>
                    <button type="button" class="btn btn-tool" data-card-widget="remove">
                      <i class="fas fa-times"></i>
                    </button>
                  </div>
                </div>
                <!-- 卡片1 內容 -->
                <div class="card-body ">
                  <div class="row">
                    <div class="col-12">
                      <div class="form-group">
                       <!-- ==================廣告名稱(卡片抬頭) ============================================-->
                        <label>廣告名稱</label>
                        <div class="input-group">
                          <div class="input-group-prepend">
                            <span class="input-group-text">
                              <i class="far fa-lightbulb"></i>
                            </span>
                          </div>
                          <input type="text" class="form-control float-right cardName">
                        </div>
                        <br>
                        <!-- ==================廣告上架區間 ============================================-->
                        <label>廣告上架區間</label>
                        <div class="input-group">
                          <div class="input-group-prepend">
                            <span class="input-group-text">
                              <i class="far fa-calendar-alt"></i>
                            </span>
                          </div>
                          <input type="text" class="form-control float-right" id="reservation">
                        </div>
                        <br>
                        <!-- =============廣告天數(自動跳轉) ============================================-->
                        <label>廣告天數</label>
                        <div class="input-group">
                          <div class="input-group-prepend">
                            <span class="input-group-text">
                              <i class="far fa-clock"></i>
                            </span>
                          </div>
                          <input type="text" id="advertisementDuringTime" class="form-control float-right" readonly>
                        </div>
                        <br>

                        <!-- ================上傳廣告橫幅 ============================================-->
                        <label for="ADInputFile">上傳廣告橫幅</label>
                        <div class="input-group">
                          <div class="custom-file">
                            <input type="file" class="custom-file-input" id="ADInputFile">
                            <label class="custom-file-label" for="ADInputFile">選擇檔案</label>
                          </div>
                          <div class="input-group-append">
                            <span class="input-group-text">上傳</span>
                          </div>
                        </div>
                        <div class=""></div>
                        <!-- ===============預覽圖片 ============================================-->
                        <br>
                        <label for="imagePreview">預覽圖片</label>
                        <div id="imagePreview"></div>
                      </div>
                      <button class="btn btn-primary">送出申請</button>
                      <!-- /.form-group -->
                    </div>
                    <!-- /.col -->
                    <div class="col-md-6">

                    </div>
                    <!-- /.col -->
                  </div>
                  <!-- /.row -->
                </div>
              </div>
              <!-- 卡片1 結尾-->
            </div>
            `
          document.getElementById('applyAD').innerHTML += createCard;

        });

        // document.getElementById('cardName').addEventListener('change',function(){
        //     cardName
        //     document.getElementById('create-card-title').innerHTML +=
        // });
      </script>
  </body>

  </html>