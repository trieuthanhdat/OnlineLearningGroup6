<jsp:include page="WebFragment/header.jsp"></jsp:include>
<jsp:include page="WebFragment/navbar.jsp"></jsp:include>
<jsp:include page="WebFragment/PopUpSignInRegister.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- Breadcrumb Start -->
<div class="breadcrumb-wrap">
    <div class="container">
        <ul class="breadcrumb">
            <li class="breadcrumb-item"><a href="HomePage">Home</a></li>
            <li class="breadcrumb-item"><a href="#">Post</a></li>
            <li class="breadcrumb-item active">Post details</li>
        </ul>
    </div>
</div>
<!-- Breadcrumb End -->

<!-- Single News Start-->
<div class="single-news">
    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <div class="sn-container">
                    <div class="sn-img">
                        <img src="assets/img/${CURRENT_POST.thumbnail}" />
                    </div>
                    <div class="sn-content">
                        <h1 class="sn-title">${CURRENT_POST.title}</h1>
                        <p>
                            ${CURRENT_POST.details.content}
                        </p>
                    </div>
                </div>
             
            </div>

            <div class="col-lg-4">
                <div class="sidebar">
                    <div class="sidebar-widget">
                        <h2 class="sw-title">In This Category</h2>
                        <div class="news-list">
                            <c:forEach items="${POSTS_OF_CATEGORY}" var="list">
                                <div class="nl-item">
                                    <div class="nl-img">
                                        <img src="assets/img/${list.thumbnail}" />
                                    </div>
                                    <div class="nl-title">
                                        <a href="PostDetails?txtPostID=${list.postID}"
                                           >${list.title}</a>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                    </div>

                    <div class="sidebar-widget">
                        <div class="image">
                            <a href="#"
                               ><img src="assets/img/ads-2.jpg" alt="Image"
                                  /></a>
                        </div>
                    </div>

                    <div class="sidebar-widget">
                        <div class="tab-news">
                            <ul class="nav nav-pills nav-justified">
                                <li class="nav-item">
                                    <a
                                        class="nav-link active"
                                        data-toggle="pill"
                                        href="#featured"
                                        >Featured</a
                                    >
                                </li>

                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="pill" href="#latest"
                                       >Latest</a
                                    >
                                </li>
                            </ul>

                            <div class="tab-content" style="min-height: fit-content!important">
                                <div id="featured" class="container tab-pane active">
                                    <c:forEach items="${FEATURED_POSTS}" var="list">
                                        <div class="tn-news">
                                            <div class="tn-img">
                                                <img src="assets/img/${list.thumbnail}" />
                                            </div>
                                            <div class="tn-title">
                                                <a href="PostDetails?txtPostID=${list.postID}">${list.title}</a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>

                                <div id="latest" class="container tab-pane fade">
                                    <c:forEach items="${RECENT_POSTS}" var="list">
                                        <div class="tn-news">
                                            <div class="tn-img">
                                                <img src="assets/img/${list.thumbnail}" />
                                            </div>
                                            <div class="tn-title">
                                                <a href="PostDetails?txtPostID=${list.postID}">${list.title}</a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="sidebar-widget">
                        <div class="image">
                            <a href="https://htmlcodex.com"
                               ><img src="assets/img/ads-2.jpg" alt="Image"
                                  /></a>
                        </div>
                    </div>

                    <div class="sidebar-widget">
                        <h2 class="sw-title">News Category</h2>
                        <div class="category">
                            <ul>
                                <li><a href="">National</a><span>(98)</span></li>
                                <li><a href="">International</a><span>(87)</span></li>
                                <li><a href="">Economics</a><span>(76)</span></li>
                                <li><a href="">Politics</a><span>(65)</span></li>
                                <li><a href="">Lifestyle</a><span>(54)</span></li>
                                <li><a href="">Technology</a><span>(43)</span></li>
                                <li><a href="">Trades</a><span>(32)</span></li>
                            </ul>
                        </div>
                    </div>

                    <div class="sidebar-widget">
                        <div class="image">
                            <a href="https://htmlcodex.com"
                               ><img src="assets/img/ads-2.jpg" alt="Image"
                                  /></a>
                        </div>
                    </div>

                    <div class="sidebar-widget">
                        <h2 class="sw-title">Tags Cloud</h2>
                        <div class="tags">
                            <a href="">National</a>
                            <a href="">International</a>
                            <a href="">Economics</a>
                            <a href="">Politics</a>
                            <a href="">Lifestyle</a>
                            <a href="">Technology</a>
                            <a href="">Trades</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Single News End-->
<jsp:include page="WebFragment/footer.jsp"></jsp:include>
