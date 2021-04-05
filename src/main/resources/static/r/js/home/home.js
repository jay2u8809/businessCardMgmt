(function() {

    $('commonLogoutBtn').on('click', function(){
        logoutValid();
    });
    $('logoutBtn').on('click', function() {
        logoutValid();
    });
})();

/*
 * Block back btn
 */
function blockBackBtn() {
    history.pushState(null, null, location.href);

    window.onpopstate = function(event) {

        history.go(1);
    };
}

/*
 * logout
 */
function logoutValid() {

    if(confirm("Logout?")) {

        location.href="users/logout";
        return true;
    }

    return false;
}
