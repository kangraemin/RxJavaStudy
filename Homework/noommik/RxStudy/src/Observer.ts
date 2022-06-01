export interface Subject {
	subscribe(observer: Observer): void
	unsubscribe(observer: Observer): void
	notify(): void
}

export interface Observer {
	update(): void
}
