import axios from 'axios'
import { item } from '@/types/base-type'

export default class TodoApi {
  private static apiHost = 'http://localhost:8080'

  private static apiService = axios.create({
    baseURL: this.apiHost,
    timeout: 1000
  })

  public static async getTodoList (): Promise<item[]> {
    const result = await this.apiService.get('/todo')
    return result.data
  }

  public static async createTodo (todoTitle: string): Promise<item> {
    return await this.apiService.post('/todo', { title: todoTitle })
  }

  public static async deleteTodo (itemId: number): Promise<void> {
    await this.apiService.delete(`/todo/${itemId}`)
  }
}
