<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="WebFragment/DashboardHeader.jsp"></jsp:include>

<jsp:include page="WebFragment/DashboardSidebar.jsp"></jsp:include>

<jsp:include page="WebFragment/DashboardTopbar.jsp"></jsp:include>

<div class="py-4">
    <div class="row">
        <div class="table-responsive">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-12">
                            <h1 class="border-3 text-center" style="font-size: 3rem"><b>Import Questions</b></h1>
                        </div>
                        <div class="col-9">
                            <a href="WEB-INF/ImportQuestionTemplate.txt" download="ImportQuestionTemplate.txt">
                            <button                                
                                class="btn btn-light"                                
                                ><i class="fa fa-download mr-1"></i>
                                <span>Download Template</span></button>
                            </button>
                            </a>
                            
                            <label class="btn btn-dark">                                
                                <form method="POST" action="UploadQuestions" enctype="multipart/form-data">
                                    <i class="fa fa-upload mr-1"></i>
                                    Upload New Questions File <input type="file" id="input-file" name="ImportQuestionFile" hidden>
                                    <input type="submit" id="submit-btn" hidden value="Upload"/>
                                </form>                                                                 
                            </label>       
                        </div>
                    </div>
                          
                    <div>
                        <!-- New Question Table -->
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table responsive">
                            <table
                                class="table table-striped table-hover text-center"
                               
                                cellspacing="0"
                                width="100%"
                            >
                                <thead>
                                    <tr>                                        
                                        <th>No</th>
                                        <th>Content</th>                                        
                                        <th>Lesson ID</th>                                                                                
                                        <th>Options</th>
                                        <th>Level</th>
                                        <th>Explanation</th>
                                        <th>Status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${empty NEW_QUESTION_LIST}">
                                        <tr>
                                            <td colspan="9" class="text-center" style="font-weight: bolder;">THERE'S NOTHING HERE YET? TRY IMPORT SOME QUESTIONS</td>
                                        </tr>                                        
                                    </c:if>
                                        
                                    <c:if test="${not empty NEW_QUESTION_LIST}">
                                    <c:forEach var="question" items="${NEW_QUESTION_LIST}" varStatus="counter">
                                        <tr>
                                            <td class="fw-bold wrap-me">${counter.count}.</td>
                                            <td class="wrap-me" style="white-space:pre-line; word-break: break-all; min-width: 150px">${question.content}</td>                                            
                                            <td class="wrap-me" style="white-space:pre-line; word-break: break-all; min-width: 30px">${question.lessonID}</td>                                            
                                            <td>
                                                <table class="table table-hover text-center" style="min-width: fit-content;padding=0; margin: 0">
                                                    <c:if test="${empty question.options}">
                                                        <tr>
                                                            <td>No options!</td>
                                                        </tr>
                                                    </c:if>
                                                    <c:forEach var="option" items="${question.options}">
                                                        <tbody>
                                                            <tr>
                                                                <td class="wrap-me" style="white-space:pre-line; word-break: break-all; min-width: 200px">${option.optionLetter}. ${option.content}</td>
                                                                <td class="wrap-me">
                                                                    <c:if test="${option.isCorrect}">&#10004;</c:if>
                                                                    <c:if test="${!option.isCorrect}">&#10060;</c:if>
                                                                </td>
                                                            </tr>                                                
                                                        </tbody>
                                                    </c:forEach>
                                                </table>
                                            </td>                                            
                                            <td class="wrap-me" style="white-space:pre-line; word-break: break-all; min-width: 30px">${question.level}</td>
                                            <td class="wrap-me" style="white-space:pre-line; word-break: break-all; min-width: 200px">${question.explanation}</td>
                                            
                                            <c:if test="${question.status}">
                                                <td>
                                                    <button class="btn btn-success" disabled>Valid</button>                                                    
                                                </td>
                                            </c:if>
                                            <c:if test="${!question.status}">
                                                <td>
                                                    <button class="btn btn-danger" disabled>Invalid</button>
                                                </td>
                                            </c:if>                                           
                                    </c:forEach>
                                    </c:if>
                                </tbody>
                            </table> 
                        </div>
                        <!-- End New Question Table -->                            
                    </div>
                    
                    <c:if test="${not empty NEW_QUESTION_LIST}">
                    <div class="submit-part justify-content-end mr-5" style="display: flex;">
                        <a href="AddNewQuestions">
                            <button class="btn btn-success">                                
                                <span>Save to Question Bank</span>
                            </button>                            
                        </a>
                    </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

</div>
</main>

<script>
    var submitBtn = document.getElementById('submit-btn');
    var inputFile = document.getElementById('input-file');
    
    inputFile.addEventListener('change', function(){
        submitBtn.click();
    });    
</script>

<%--<jsp:include page="WebFragment/DashboardFooter.jsp"></jsp:include>--%>
<%--<jsp:include page="WebFragment/DashboardConfiguration.jsp"></jsp:include>--%>
<!--   Core JS Files   -->
<script src="DashboardAssets/js/core/popper.min.js"></script>
<script src="DashboardAssets/js/core/bootstrap.min.js"></script>
<script src="DashboardAssets/js/plugins/smooth-scrollbar.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<!-- Edit/add/delete modal -->
<!-- jquery
            ============================================ -->
<script src="DashboardAssets/js/vendor/jquery-1.12.4.min.js"></script>
<!-- bootstrap JS
            ============================================ -->
<script src="DashboardAssets/js/bootstrap.min.js"></script>
<!-- wow JS
            ============================================ -->
<script src="DashboardAssets/js/wow.min.js"></script>
<!-- price-slider JS
            ============================================ -->
<script src="DashboardAssets/js/jquery-price-slider.js"></script>
<!-- meanmenu JS
            ============================================ -->
<script src="DashboardAssets/assets/js/jquery.meanmenu.js"></script>
<!-- owl.carousel JS
            ============================================ -->
<script src="DashboardAssets/js/owl.carousel.min.js"></script>
<!-- sticky JS
            ============================================ -->
<script src="DashboardAssets/js/jquery.sticky.js"></script>
<!-- scrollUp JS
            ============================================ -->
<script src="DashboardAssets/js/jquery.scrollUp.min.js"></script>
<!-- mCustomScrollbar JS
            ============================================ -->
<script src="DashboardAssets/js/scrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="DashboardAssets/js/scrollbar/mCustomScrollbar-active.js"></script>
<!-- metisMenu JS
            ============================================ -->
<script src="DashboardAssets/js/metisMenu/metisMenu.min.js"></script>
<script src="DashboardAssets/js/metisMenu/metisMenu-active.js"></script>
<!-- data table JS
            ============================================ -->
<script src="DashboardAssets/js/data-table/bootstrap-table.js"></script>
<script src="DashboardAssets/js/data-table/tableExport.js"></script>
<script src="DashboardAssets/js/data-table/data-table-active.js"></script>
<script src="DashboardAssets/js/data-table/bootstrap-table-editable.js"></script>
<script src="DashboardAssets/js/data-table/bootstrap-editable.js"></script>
<script src="DashboardAssets/js/data-table/bootstrap-table-resizable.js"></script>
<script src="DashboardAssets/js/data-table/colResizable-1.5.source.js"></script>
<script src="DashboardAssets/js/data-table/bootstrap-table-export.js"></script>
<!--  editable JS
            ============================================ -->
<script src="DashboardAssets/js/editable/jquery.mockjax.js"></script>
<script src="DashboardAssets/js/editable/mock-active.js"></script>
<script src="DashboardAssets/js/editable/select2.js"></script>
<script src="DashboardAssets/js/editable/moment.min.js"></script>
<script src="DashboardAssets/js/editable/bootstrap-datetimepicker.js"></script>
<script src="DashboardAssets/js/editable/bootstrap-editable.js"></script>
<script src="DashboardAssets/js/editable/xediable-active.js"></script>
<!-- Chart JS
            ============================================ -->
<script src="DashboardAssets/js/chart/jquery.peity.min.js"></script>
<script src="DashboardAssets/js/peity/peity-active.js"></script>
<!-- tab JS
            ============================================ -->
<script src="DashboardAssets/js/tab.js"></script>
<!-- plugins JS
            ============================================ -->
<script src="DashboardAssets/js/plugins.js"></script>
<!-- main JS
            ============================================ -->
<script src="DashboardAssets/js/main.js"></script>
<!-- tawk chat JS
            ============================================ -->
<!--<script src="DashboardAssets/js/tawk-chat.js"></script>-->

<script>
                        $(document).ready(function () {
                            // Activate tooltip
                            $('[data-toggle="tooltip"]').tooltip();

                            // Select/Deselect checkboxes
                            var checkbox = $('table tbody input[type="checkbox"]');
                            $("#selectAll").click(function () {
                                if (this.checked) {
                                    checkbox.each(function () {
                                        this.checked = true;
                                    });
                                } else {
                                    checkbox.each(function () {
                                        this.checked = false;
                                    });
                                }
                            });
                            checkbox.click(function () {
                                if (!this.checked) {
                                    $("#selectAll").prop("checked", false);
                                }
                            });
                        });
</script>
<!-- MDB -->
<%--
<script
    type="text/javascript"
    src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.6.0/mdb.min.js"
></script>
--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- END  MDB -->
<script
    src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"
></script>
<script
    src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
    integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
    crossorigin="anonymous"
></script>
<script
    src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
    crossorigin="anonymous"
></script>
<script>
                        var win = navigator.platform.indexOf("Win") > -1;
                        if (win && document.querySelector("#sidenav-scrollbar")) {
                            var options = {
                                damping: "0.5",
                            };
                            Scrollbar.init(document.querySelector("#sidenav-scrollbar"), options);
                        }
</script>
<!-- Github buttons -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
<!-- Control Center for Soft Dashboard: parallax effects, scripts for the example pages etc -->
<script src="DashboardAssets/js/soft-ui-dashboard.min.js?v=1.0.2"></script>
</body>
</html>