<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Button trigger New Lesson modal -->
    
    <!-- New Lesson Modal HTML -->
    <div id="addLessonModal" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <form action="AddNewLesson" method="POST">
            <div class="modal-header">
              <h4 class="modal-title">Add New Lesson</h4>
                            
              <button
                type="button"
                class="close"                  
                aria-hidden="true"
                data-dismiss="modal"
              >
                &times;
              </button>
              
            </div>
              
            <div class="modal-body">
              <div class="form-group">
                  <label>Lesson Name: (*)</label>
                  <input type="text" class="form-control" required
                      name="txtLessonName"
                      value=""                    
                  />
              </div>
              <div class="form-group">
                  <label>Type: (*)</label>
                  <select class="form-select" id="new-type-options" name="txtLessonType" required>
                      <option value="" style="font-weight: bold;">Select a type</option>
                      <option value="Subject Topic">Subject Topic</option>
                      <option value="Lesson">Lesson</option>
                      <option value="Quiz">Quiz</option>
                  </select>
              </div>
              <div class="form-group">
                  <label>Topic: (*)</label>
                  <select class="form-select" id="new-select-topic-dropdown" name="txtTopicID" required>
                      <option value="" style="font-weight: bold;">Select a topic</option>
                      <c:forEach var="dto" items="${TOPICS_LIST}">
                          <option value="${dto.lessonID}">${dto.name}</option> 
                      </c:forEach>                                                                    
                  </select>
              </div>
              <div class="form-group">
                  <label>Order: (*)</label>
                  <input type="text" class="form-control" required 
                      name="txtOrder"
                      value=""
                  />
              </div>
              <div id="new-video-link" class="form-group">
                  <label>Video Link:</label>
                  <input type="text" class="form-control" 
                      name="txtVideoLink"
                      value=""
                  />
              </div>
              <div id="new-html-content" class="form-group">
                  <label>Content: (*)</label>
                  <input type="text" class="form-control" required 
                      name="txtHtmlContent"
                      value=""
                  />
              </div>
              <div id="new-quizzes" class="form-group">
                  <label>Quiz: (*)</label>
                  <select class="form-select" id="new-quiz-dropdown" name="txtQuizID" required>
                      <option value="" style="font-weight: bold;">Select a quiz</option>
                      <c:forEach var="dto" items="${QUIZZES_LIST}">
                          <option value="${dto.quizID}">${dto.name}</option> 
                      </c:forEach>                                                                    
                  </select>
              </div>
            </div>
              
            <div class="modal-footer">              
              <input
                type="button"
                class="btn btn-default"                  
                data-dismiss="modal"
                value="Back"
              />              
                            
              <input type="submit" class="btn btn-info" value="Save" />               
            </div>
          </form>
        </div>
      </div>
    </div>
    <!--END Edit Modal HTML -->
