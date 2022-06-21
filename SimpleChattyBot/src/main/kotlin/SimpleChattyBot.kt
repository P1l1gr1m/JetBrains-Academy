package bot

fun main() {
    println("Hello! My name is Tod.")
    println("I was created in 2022.")
    println("Please, remind me your name.")
    val name = readln()
    println("What a great name you have, ${name}!")
    println("Let me guess your age.")
    println("Enter remainders of dividing your age by 3, 5 and 7.")
    val remainder3 = readln().toInt()
    val remainder5 = readln().toInt()
    val remainder7 = readln().toInt()
    var age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105
    println("Your age is ${age}; that's a good time to start programming!")
    println("Now I will prove to you that I can count to any number you want.")
    val number = readln().toInt()
    for (num in 0..number) {
        print(num)
        println("!")
    }
    println("Let's test your programming knowledge.")
    println("Why do we use methods?")
    println("1. To repeat a statement multiple times.\n" +
            "2. To decompose a program into several small subroutines.\n" +
            "3. To determine the execution time of a program.\n" +
            "4. To interrupt the execution of a program.")
    do {
        val choice = readln().toInt()
        if (choice == 1) {
            println("Please, try again.")
        }
        if (choice == 3) {
            println("Please, try again.")
        }
        if (choice == 4) {
            println("Please, try again.")
        }
        if (choice == 2) {
            println("Congratulations, have a nice day!")
        }
    } while (choice != 2)
}