

    <!-- Button trigger user profile modal -->
    <button
        hidden
        type="button"
        class="btn btn-primary"
        data-toggle="modal"
        data-target="#userProfileModal"
        id="userProfileBtn"
        >
        Subject Details
    </button>
    
    <!-- Edit Modal HTML -->
    <div id="userProfileModal" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <form>
            <div class="modal-header">
              <h4 class="modal-title">User Profile</h4>
              
              <a href="ClearProfileCache">
                <button
                  type="button"
                  class="close"                  
                  aria-hidden="true"
                >
                  &times;
                </button>
              </a>
            </div>
            <div class="modal-body">
              <div class="form-group">
                <label>Name</label>
                <input type="text" class="form-control" required 
                    value="${SEE_USER.fullName}"                    
                />
              </div>
              <div class="form-group">
                <label>Email</label>
                <input type="email" class="form-control" required 
                    value="${SEE_PROFILE.email}"
                />
              </div>
              <div class="form-group">
                <label>Phone</label>
                <input type="text" class="form-control" required 
                  value="${SEE_PROFILE.phone}"
                />
              </div>
              <div class="form-group">
                <label>Address</label>
                <textarea class="form-control" required>${SEE_PROFILE.address}</textarea>
              </div>
                
            </div>
            <div class="modal-footer">
              <a href="ClearProfileCache">
                <input
                  type="button"
                  class="btn btn-default"                  
                  value="Back"
                />
              </a>
              
              <%-- 
              <input type="submit" class="btn btn-info" value="Save" /> 
              --%>
            </div>
          </form>
        </div>
      </div>
    </div>
    <!--END Edit Modal HTML -->