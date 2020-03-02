/**
 *
 */
var count = 1;
var isName = 0;
var isCat = 0;
var category;
var catName;
function profileOnLoad(){



    $.ajax({
            url: "/quizProject_war_exploded/ProfileServlet",
            dataType: "json",
            method: "post",
            data: {
                index: count
            },
            success: function (data) {
                console.log(data);

                data.forEach(obj => {
                    let statuscode;
                    let buttoncode;
                    if(obj.status==1){
                        statuscode = "Active";
                        buttoncode = "Suspend";
                    }else{
                        statuscode = "Suspended";
                        buttoncode = "Activate";
                    }
                    var table = document.getElementById("myTable").getElementsByTagName('tbody')[0];
                    var row = table.insertRow();
                    var cell1 = row.insertCell(0);
                    var cell2 = row.insertCell(1);
                    var cell3 = row.insertCell(2);
                    var cell4 = row.insertCell(3);
                    var cell5 = row.insertCell(4);
                    var cell6 = row.insertCell(5);
                    var cell7 = row.insertCell(6);
                    cell1.innerHTML = obj.firstName;
                    cell2.innerHTML = obj.lastName;
                    cell3.innerHTML = obj.address;
                    cell4.innerHTML = obj.Email;
                    cell5.innerHTML = obj.phoneNumber;
                    cell6.innerHTML = "<p id='id"+ obj.id +"'>"+statuscode+"</p>";
                    cell7.innerHTML = "<button style='color:black;' onclick='activate(this)' value='"+ obj.id+"'>"+buttoncode +"</button>";


                });
            }
        });





// <th scope="col">Taken Date</th>
//     <th scope="col">Category</th>
//         <th scope="col">User Name</th>
//     <th scope="col">Score</th>
//         <th scope="col">View Detail</th>



};

function historyOnLoad(){


    $.ajax({
        url: "/quizProject_war_exploded/HistoryServlet",
        dataType: "json",
        method: "post",
        data: {
            index: count,
            name: "none"
        },
        success: function (data) {
            console.log(data);
            var counter = 0;
            data.forEach(obj => {

                var table = document.getElementById("historyTable").getElementsByTagName('tbody')[0];
                var row = table.insertRow();
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);

                // var date = JSON.stringify(obj.startTime);

                cell1.innerHTML = obj.startTime;
                cell2.innerHTML = obj.quiz.name;
                cell3.innerHTML = obj.quizUser.firstName +" " +obj.quizUser.lastName;
                cell4.innerHTML = obj.score;
                cell5.innerHTML = "<button style='color:black;' onclick='viewDetail(this)' value='"+ counter+"'>View</button>";

                counter++;
            });
        }
    });
};

function preProfile(){
    if(count>1){
        count--;
    }else {
        alert("On the first page");
        return;
    }
    var table = document.getElementById("myTable").getElementsByTagName('tbody')[0];
    table.innerHTML=""
    $.ajax({
        url: "/quizProject_war_exploded//ProfileServlet",
        dataType: "json",
        method: "post",
        data: {
            index: count
        },
        success: function (data) {
            console.log(data);

            data.forEach(obj => {
                let statuscode;
                let buttoncode;
                if(obj.status==1){
                    statuscode = "Active";
                    buttoncode = "Suspend";
                }else{
                    statuscode = "Suspended";
                    buttoncode = "Activate";
                }


                var row = table.insertRow();
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);
                var cell6 = row.insertCell(5);
                var cell7 = row.insertCell(6);
                cell1.innerHTML = obj.firstName;
                cell2.innerHTML = obj.lastName;
                cell3.innerHTML = obj.address;
                cell4.innerHTML = obj.Email;
                cell5.innerHTML = obj.phoneNumber;
                cell6.innerHTML = "<p id='id"+ obj.id +"'>"+statuscode+"</p>";
                cell7.innerHTML = "<button style='color:black;' onclick='activate(this)' value='"+ obj.id+"'>"+buttoncode +"</button>";
            });
        }
    });
};

function nextProfile(){
    if($('#myTable tbody').find('tr').length==10){
        count++;
    }else{
        alert("On the last page...");
        return;
    }
    var table = document.getElementById("myTable").getElementsByTagName('tbody')[0];
    table.innerHTML="";
    $.ajax({
        url: "/quizProject_war_exploded//ProfileServlet",
        dataType: "json",
        method: "post",
        data: {
            index: count
        },
        success: function (data) {
            console.log(data);

            data.forEach(obj => {
                let statuscode;
                let buttoncode;
                if(obj.status==1){
                    statuscode = "Active";
                    buttoncode = "Suspend";
                }else{
                    statuscode = "Suspended";
                    buttoncode = "Activate";
                }


                var row = table.insertRow();
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);
                var cell6 = row.insertCell(5);
                var cell7 = row.insertCell(6);
                cell1.innerHTML = obj.firstName;
                cell2.innerHTML = obj.lastName;
                cell3.innerHTML = obj.address;
                cell4.innerHTML = obj.Email;
                cell5.innerHTML = obj.phoneNumber;
                cell6.innerHTML = "<p id='id"+ obj.id +"'>"+statuscode+"</p>";
                cell7.innerHTML = "<button style='color:black;' onclick='activate(this)' value='"+ obj.id+"'>"+buttoncode +"</button>";


            });
        }
    });

};

function activate(link){

    console.log($(link).val());
    var column = document.getElementById("id"+$(link).val());
    var value;
    if($(link).text()=="Activate"){
        value = 1;
    }else{
        value =0;
    }


    $.ajax({
        url: "/quizProject_war_exploded//ProfileServlet",
        method: "get",
        data: {
            value: value,
            id: $(link).val()
        },
        success: function () {
            console.log("in success")
            if($(link).text()=="Activate"){
                $(link).html("Suspend");
                column.innerHTML="<p>Active</p>"

            }else{
                $(link).html("Activate");
                column.innerHTML="<p>Suspended</p>"
            }
        }
    });

}

function viewDetail(link){
    console.log($(link).val());
    $.ajax({
        url: "/quizProject_war_exploded/HistoryServlet",
        method: "get",
        data: {
            whichHistory: $(link).val()
        },
        success:function () {
            window.location.href = "/quizProject_war_exploded/historyResult.jsp";

        }
    });
};




function preHistory() {
    var name="none";
    if(isName == 1){
        name = "name";
    }else if(isCat==1){
        name = "cat";
    }

    if(count>1){
        count--;
    }else {
        alert("On the first page");
        return;
    }
    var table = document.getElementById("historyTable").getElementsByTagName('tbody')[0];
    table.innerHTML=""
    $.ajax({
        url: "/quizProject_war_exploded/HistoryServlet",
        dataType: "json",
        method: "post",
        data: {
            index: count,
            name: name
        },
        success: function (data) {
            console.log(data);
            var counter = 0;
            data.forEach(obj => {

                var row = table.insertRow();
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);

                // var date = JSON.stringify(obj.startTime);

                cell1.innerHTML = obj.startTime;
                cell2.innerHTML = obj.quiz.name;
                cell3.innerHTML = obj.quizUser.firstName + " " + obj.quizUser.lastName;
                cell4.innerHTML = obj.score;
                cell5.innerHTML = "<button style='color:black;' onclick='viewDetail(this)' value='" + counter + "'>View</button>";

                counter++

            });
        }

    });
}


function nextHistory(){
    if($('#historyTable tbody').find('tr').length==10){
        count++;
    }else{
        alert("On the last page...");
        return;
    }

    var name="none";
    if(isName == 1){
        name = "name";
    }else if(isCat==1){
        name = "cat";
    }

    var table = document.getElementById("historyTable").getElementsByTagName('tbody')[0];
    table.innerHTML="";
    $.ajax({
        url: "/quizProject_war_exploded/HistoryServlet",
        dataType: "json",
        method: "post",
        data: {
            index: count,
            name: name
        },
        success: function (data) {
            console.log(data);
            var counter=0;
            data.forEach(obj => {

                var row = table.insertRow();
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);

                // var date = JSON.stringify(obj.startTime);

                cell1.innerHTML = obj.startTime;
                cell2.innerHTML = obj.quiz.name;
                cell3.innerHTML = obj.quizUser.firstName + " " + obj.quizUser.lastName;
                cell4.innerHTML = obj.score;
                cell5.innerHTML = "<button style='color:black;' onclick='viewDetail(this)' value='" + counter + "'>View</button>";

                counter++;
            });
        }

    });

}

function sortByCategory(){
    count =1;
    isName=0;
    var name
    if(isCat==1){
        isCat=0;
        name = "none";
    }else{
        isCat=1;
        name="cat";
    }
    var table = document.getElementById("historyTable").getElementsByTagName('tbody')[0];
    table.innerHTML=""

    $.ajax({
        url: "/quizProject_war_exploded/HistoryServlet",
        dataType: "json",
        method: "post",
        data: {
            index: count,
            name: name
        },
        success: function (data) {
            console.log(data);
            var counter=0;
            data.forEach(obj => {

                var row = table.insertRow();
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);

                // var date = JSON.stringify(obj.startTime);

                cell1.innerHTML = obj.startTime;
                cell2.innerHTML = obj.quiz.name;
                cell3.innerHTML = obj.quizUser.firstName + " " + obj.quizUser.lastName;
                cell4.innerHTML = obj.score;
                cell5.innerHTML = "<button style='color:black;' onclick='viewDetail(this)' value='" + counter + "'>View</button>";

                counter++;
            });
        }

    });

}
function sortByName(){
    count =1;
    var name;

    if(isName==1){
        isName=0;
        name = "none";
    }else{
        isName=1;
        name="name"
    }
    var table = document.getElementById("historyTable").getElementsByTagName('tbody')[0];
    table.innerHTML="";

    isCat=0;
    $.ajax({
        url: "/quizProject_war_exploded/HistoryServlet",
        dataType: "json",
        method: "post",
        data: {
            index: count,
            name: name
        },
        success: function (data) {
            console.log(data);
            var counter=0;
            data.forEach(obj => {

                var row = table.insertRow();
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);

                // var date = JSON.stringify(obj.startTime);

                cell1.innerHTML = obj.startTime;
                cell2.innerHTML = obj.quiz.name;
                cell3.innerHTML = obj.quizUser.firstName + " " + obj.quizUser.lastName;
                cell4.innerHTML = obj.score;
                cell5.innerHTML = "<button style='color:black;' onclick='viewDetail(this)' value='" + counter + "'>View</button>";

                counter++;
            });
        }

    });
}


$( function() {
    $.widget( "custom.catcomplete", $.ui.autocomplete, {
        _create: function() {
            this._super();
            this.widget().menu( "option", "items", "> :not(.ui-autocomplete-category)" );
        },
        _renderMenu: function( ul, items ) {
            var that = this,
                currentCategory = "";
            $.each( items, function( index, item ) {
                var li;
                if ( item.category != currentCategory ) {
                    ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
                    currentCategory = item.category;
                }
                li = that._renderItemData( ul, item );
                if ( item.category ) {
                    li.attr( "aria-label", item.category + " : " + item.label );
                }
            });
        }
    });

    $( "#search" ).catcomplete({
        delay: 0,
        source: function( request, response ) {

            $.ajax({
                url: "/quizProject_war_exploded/SearchServlet",
                dataType: "json",
                method:"post",
                async:true,
                data: {
                    search: request.term
                },
                success: function( data ) {
                    response( data );
                }
            });
        },
        select: function(event,ui){
            console.log(event);
            console.log(ui.item.category);
            $.ajax({
                url: '/quizProject_war_exploded/SearchServlet',
                data: {label: ui.item.label,
                    category: ui.item.category,
                    index: count=1
                },
                dataType: 'json',
                type: 'get',
                success: function(data) {
                    var table = document.getElementById("historyTable").getElementsByTagName('tbody')[0];
                    table.innerHTML="";
                    count =1;
                    var counter=0;
                    category = ui.item.category;
                    catName = ui.item.label;
                    data.forEach(obj => {

                        var row = table.insertRow();
                        var cell1 = row.insertCell(0);
                        var cell2 = row.insertCell(1);
                        var cell3 = row.insertCell(2);
                        var cell4 = row.insertCell(3);
                        var cell5 = row.insertCell(4);

                        // var date = JSON.stringify(obj.startTime);

                        cell1.innerHTML = obj.startTime;
                        cell2.innerHTML = obj.quiz.name;
                        cell3.innerHTML = obj.quizUser.firstName + " " + obj.quizUser.lastName;
                        cell4.innerHTML = obj.score;
                        cell5.innerHTML = "<button style='color:black;' onclick='viewDetail(this)' value='" + counter + "'>View</button>";

                        counter++

                    });
                }
            });
        }
    });
} );


// <tr>
// <td><c:out value="${cur.getId()}" /></td>
//     <td><c:out value="${cur.getFirstName()}" /></td>
//     <td><c:out value="${cur.getLastName()}" /></td>
//     <td><c:out value="${cur.getPhoneNumber()}" /></td>
//     <td><c:out value="${cur.getEmail()}" /></td>
//     <td><c:out value="${cur.getDate()}" /></td>
//     </tr>

//
// <th scope="col">FirstName</th>
//     <th scope="col">LastName</th>
//     <th scope="col">Address</th>
//     <th scope="col">Email</th>
//     <th scope="col">PhoneNumber</th>
//     <th scope="col">Status</th>
//     <th scope="col">activate/suspend</th>