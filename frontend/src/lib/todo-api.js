import axios from 'axios'

export default class TodoApi {
    static apiHost = 'http://localhost:8080'

    static apiService = axios.create({
      baseURL: this.apiHost,
      timeout: 1000
    })

    static async getTodoList () {
      const result = await this.apiService.get('/todo')
      return result.data
    }

    static async createTodo (todoTitle) {
      const result = await this.apiService.post('/todo', { title: todoTitle })
      return result.data
    }

    static async deleteTodo (itemId) {
      await this.apiService.delete(`/todo/${itemId}`)
    }
}
