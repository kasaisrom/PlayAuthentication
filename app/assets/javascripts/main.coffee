$ ->
  $.get "/users", (users) ->
    $.each users, (index, user) ->
     $(".table").append $("<tr>").text user.email