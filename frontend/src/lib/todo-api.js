import axios from 'axios'

const apiHost = 'http://localhost:8080'

const apiService = axios.create({
  baseURL: apiHost,
  timeout: 1000
})

apiService.interceptors.response.use(function (response) {
  return response
}, function (error) {
  if (error.response.status === 403) {
    window.location.href = '/#/login'
  }
  return Promise.reject(error)
})

const getToken = function () {
  const token = sessionStorage.getItem('ACCESS_TOKEN')
  if (token && token !== null) {
    return `Bearer ${token}`
  }
  return null
}

const TodoApi = {
  GET: async function getTodoList () {
    console.log(`Token : ${getToken()}`)
    const result = await apiService.get('/todo', { headers: { Authorization: getToken() } })
    console.log(`Get : ${JSON.stringify(result.data)}`)
    return result.data
  },

  POST: async function createTodo (todoTitle) {
    const result = await apiService.post('/todo', { title: todoTitle }, { headers: { Authorization: getToken() } })
    return result.data
  },

  DELETE: async function deleteTodo (itemId) {
    await apiService.delete(`/todo/${itemId}`, { headers: { Authorization: getToken() } })
  },

  SignIn: async function signin (userDto) {
    apiService.post('/signin', userDto).then(
      (response) => {
        if (response.data.token) {
          sessionStorage.setItem('ACCESS_TOKEN', response.data.token)
          window.location.href = '/#/'
          alert('token' + response.data.token)
        }
      }
    )
  }
}

export { TodoApi }
