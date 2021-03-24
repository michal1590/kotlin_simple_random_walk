fun main() {
    val size = getBoardSize()
    val game = Fractal(size)

    game.showBoard("Init")
    game.play(10)

    finish()
}


class Fractal(private val size: Int){
    private val board: List<IntArray> = List(size) {IntArray(size) {0} }
    private var headX = (0 until size).random()
    private var headY = (0 until size).random()

    init {
        board[headY][headX] = 1
    }

    fun showBoard(text: String) {
        println("\n\n$text Board")
        for (row in board){
            for (element in row){
                print("  ")
                print(element)
            }
            println()
        }
    }

    fun play(roundsLimit: Int){
        var roundCount = 0

        while (roundCount < roundsLimit){

            roundCount++

            val allowedMoves = findRightMoves()

            if (allowedMoves.size == 0){
                println("Game Over")
                break
            }

            val selectedMove = selectMove(allowedMoves)
            println("Move $selectedMove")

            makeMove(selectedMove)

            updateBoard()

            showBoard(roundCount.toString())
        }
    }

    private fun findRightMoves(): MutableList<String> {
        val allowedMoves = mutableListOf("left", "right", "top", "bottom")
        if ((headX - 1 <= 0) || (board[headY][headX - 1] == 1)) {
            allowedMoves.remove("left")
        }

        if (headX + 1 < size) {
            if (board[headY][headX + 1] != 0) allowedMoves.remove("right")
        } else allowedMoves.remove("right")

        if ((headY - 1 <= 0) || (board[headY - 1][headX] == 1)) {
            allowedMoves.remove("top")
        }

        if (headY + 1 < size) {
            if (board[headY + 1][headX] != 0) allowedMoves.remove("bottom")
        } else allowedMoves.remove("bottom")

        return allowedMoves
    }

        private fun selectMove(moves: MutableList<String>): String{
        return moves.random()
    }

    private fun makeMove(selectedMove: String) {
        when (selectedMove) {
            "left" -> headX--
            "right" -> headX++
            "top" -> headY--
            "bottom" -> headY++
        }
    }

    private fun updateBoard() {
        board[headY][headX] = 1
    }
}


fun getBoardSize(): Int {
    println("Enter board size")
    val size = readLine()?.toIntOrNull() ?: 5
    println("Continue with size: $size")
    return size
}


fun finish(){
    println("\n\n Press Enter to finish")
    readLine()

}