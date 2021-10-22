// Selectors
const todoList = document.querySelector(".todo-list")
const todoInput = document.querySelector(".todo-input input")
const addTodo = document.querySelector(".add-todo")
const delAll = document.querySelector(".del-all")


// Functions
function addNewTodo() {
    if (todoInput.value.length === 0) {
        alert("Please Enter A Todo")
    }
    else {
        const newTodo = document.createElement("div")
        newTodo.classList.add("new-todo")

        const todo = document.createElement("input")
        todo.classList.add("todo")
        newTodo.appendChild(todo)
        todo.disabled = true
        todo.value = todoInput.value
        todoInput.value = ""

        const editTodo = document.createElement("button")
        editTodo.classList.add("edit-btn")
        newTodo.appendChild(editTodo)
        editTodo.innerHTML = '<i class="fas fa-edit"></i>'
        editTodo.setAttribute("onclick", "edit(this)")


        const delTodo = document.createElement("button")
        delTodo.classList.add("del-btn")
        newTodo.appendChild(delTodo)
        delTodo.innerHTML = '<i class="fas fa-minus-circle"></i>'
        delTodo.setAttribute("onclick", "del(this)")

        todoList.appendChild(newTodo)
    }
}

function deleteAll() {
    todoList.innerHTML = ""
}


function del(e) {
    const newTodo = e.parentNode
    newTodo.classList.add("fall")
    newTodo.addEventListener("transitionend", function () {
        newTodo.remove()
    })
}


var edited = true
function edit(e) {
    const todo = e.parentNode.firstChild

    if (edited === true) {
        todo.disabled = false
        todo.classList.add("center")
        todo.focus()
        e.firstChild.remove()
        e.innerHTML = '<i class="fas fa-check-circle"></i>'

        edited = false
    }
    else if (edited === false) {
        todo.disabled = true
        todo.classList.remove("center")
        e.firstChild.remove()
        e.innerHTML = '<i class="fas fa-edit"></i>'

        edited = true
    }

}


todoInput.addEventListener("keypress", enterKey)


function enterKey (e) {
    if (e.keyCode === 13) {
        if (todoInput.value.length === 0) {
            alert("Please Enter A Todo")
        }
        else {
            const newTodo = document.createElement("div")
            newTodo.classList.add("new-todo")
    
            const todo = document.createElement("input")
            todo.classList.add("todo")
            newTodo.appendChild(todo)
            todo.disabled = true
            todo.value = todoInput.value
            todoInput.value = ""
    
            const editTodo = document.createElement("button")
            editTodo.classList.add("edit-btn")
            newTodo.appendChild(editTodo)
            editTodo.innerHTML = '<i class="fas fa-edit"></i>'
            editTodo.setAttribute("onclick", "edit(this)")
    
    
            const delTodo = document.createElement("button")
            delTodo.classList.add("del-btn")
            newTodo.appendChild(delTodo)
            delTodo.innerHTML = '<i class="fas fa-minus-circle"></i>'
            delTodo.setAttribute("onclick", "del(this)")
    
            todoList.appendChild(newTodo)
        }
    }
}