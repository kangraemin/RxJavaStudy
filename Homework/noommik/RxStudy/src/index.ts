import { School } from './School'
import { Teacher } from './Teacher'

const school = new School()

const teacher1 = new Teacher('김선생')
const teacher2 = new Teacher('나선생')
const teacher3 = new Teacher('박선생')

school.subscribe(teacher1)
school.subscribe(teacher2)

school.notify()

school.subscribe(teacher3)
school.notify()
