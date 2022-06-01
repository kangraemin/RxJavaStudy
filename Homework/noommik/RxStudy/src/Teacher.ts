import { Observer, Subject } from './Observer'

export class Teacher implements Observer {
	public readonly name: string

	constructor(name: string) {
		this.name = name
	}

	update() {
		console.log(`update teacher:: ${this.name}`)
	}
}
