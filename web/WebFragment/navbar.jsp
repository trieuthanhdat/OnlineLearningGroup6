<!-- Nav Bar Start -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div
    class="nav-bar navbar-expand-lg navbar-dark bg-dark" id="top-navbar"
    style="padding: 0px 10%"
    >
    <div>
        <nav class="navbar navbar-expand-md bg-dark navbar-dark" >
            <a href="#" class="navbar-brand">MENU</a>
            <button
                type="button"
                class="navbar-toggler"
                data-toggle="collapse"
                data-target="#navbarCollapse"
                >
                <span class="navbar-toggler-icon"></span>
            </button>

            <div
                class="collapse navbar-collapse justify-content-between"
                id="navbarCollapse"
                >
                <div class="navbar-nav mr-auto" style="    margin: 0;
                     flex: auto;">
                    <a href="HomePage" class="nav-item nav-link active">Home</a>
                    <a href="contact" class="nav-item nav-link" style="width: 11%;">Contact us</a>
                    <div class="nav-item dropdown">
                        <a
                            href="#"
                            class="nav-link dropdown-toggle"
                            data-toggle="dropdown"
                            >Category</a
                        >
                        <ul class="dropdown-menu" style="box-shadow: -3px 0px 0px #2a2a2b52;">
                            <c:forEach items="${CATEGORY_LIST}" var="list">
                                <li>  
                                    <a class="dropdown-item" href="SearchCourse?txtCategoryID=${list.categoryID}"
                                       ><i class="fa fa-home"></i>${list.categoryName}</a>
                                </li>  
                            </c:forEach>
                        </ul>
                    </div>


                    <form action="SearchCourse" style="width: 100%;"> 
                        <div class="input-group">
                            <div class="form-outline" style="width: 90%">
                                <input
                                    type="text"
                                    name="txtCourseName"
                                    placeholder="Search"
                                    style="width:100%;min-height: 100%; border-radius: 10px 0px 0px 10px"
                                    />
                            </div>
                            <button type="submit"
                                    class="btn btn-primary"
                                    style="border-radius: 0px 10px 10px 0px;    margin-top: inherit;"
                                    >
                                <i class="fa fa-search" ></i>
                            </button>
                        </div>
                    </form>
                </div>
                <div class="social ml-auto">
                    <c:if  test="${empty CURRENT_USER}">
                        <button class="btn" id="sign-in">Join now</button>
                    </c:if>
                    <c:if test="${not empty CURRENT_USER}">
                        <div class="dropdownbox">
                            <c:if test="${empty CURRENT_USER}">
                                
                            </c:if>
                        </div>
                        <img
                            src="assets/img/Avatar/userAvatar.jpg"
                            class="avatar dropdown-toggle"
                            />
                        <div class="dropdown-content">

                            <a href="Profile" class="list-item">Profile</a>
                            <a href="MyCourse" class="list-item">My Courses</a>
                            <a href="Logout" class="list-item">Log out</a>
                        </div>
                    </div>
                </c:if>
            </div>
    </div>
</nav>
</div>
</div>
<!-- Nav Bar End -->
