import { Observer } from './Observer'

export class Student implements Observer {
	public readonly name: string

	constructor(name: string) {
		this.name = name
	}

	update() {
		console.log(`update student:: ${this.name}`)
	}
}
