package com.goboard.model

object DefaultLayout {
    val qwerty = KeyboardLayout(
        rows = listOf(
            // Top custom row
            listOf(
                Key("{", "{"),
                Key("}", "}"),
                Key("[", "["),
                Key("]", "]"),
                Key("(", "("),
                Key(")", ")"),
                Key("<", "<"),
                Key(">", ">"),
                Key("=", "="),
                Key("+", "+"),
            ),
            // Second custom row
            listOf(
                Key("_", "_"),
                Key("-", "-"),
                Key(";", ";"),
                Key(":", ":"),
                Key("\"", "\""),
                Key("'", "'"),
                Key("/", "/"),
                Key("\\", "\\"),
                Key("|", "|"),
                Key("~", "~"),
            ),
            // QWERTY row
            listOf(
                Key("q", "q"),
                Key("w", "w"),
                Key("e", "e"),
                Key("r", "r"),
                Key("t", "t"),
                Key("y", "y"),
                Key("u", "u"),
                Key("i", "i"),
                Key("o", "o"),
                Key("p", "p"),
            ),
            // ASDF row
            listOf(
                Key("a", "a"),
                Key("s", "s"),
                Key("d", "d"),
                Key("f", "f"),
                Key("g", "g"),
                Key("h", "h"),
                Key("j", "j"),
                Key("k", "k"),
                Key("l", "l"),
            ),
            // ZXC row
            listOf(
                Key("Shift", "Shift", width = 1.5f, isModifier = true),
                Key("z", "z"),
                Key("x", "x"),
                Key("c", "c"),
                Key("v", "v"),
                Key("b", "b"),
                Key("n", "n"),
                Key("m", "m"),
                Key("Del", "Del", width = 1.5f, isModifier = true),
            ),
            // Bottom row
            listOf(
                Key("?123", "?123", width = 1.5f, isModifier = true),
                Key(",", ","),
                Key(" ", " ", width = 4f),
                Key(".", "."),
                Key("Enter", "Enter", width = 1.5f, isModifier = true),
            )
        )
    )

    val qwertyShift = KeyboardLayout(
        rows = listOf(
            // Top custom row
            listOf(
                Key("{", "{"),
                Key("}", "}"),
                Key("[", "["),
                Key("]", "]"),
                Key("(", "("),
                Key(")", ")"),
                Key("<", "<"),
                Key(">", ">"),
                Key("=", "="),
                Key("+", "+"),
            ),
            // Second custom row
            listOf(
                Key("_", "_"),
                Key("-", "-"),
                Key(";", ";"),
                Key(":", ":"),
                Key("\"", "\""),
                Key("'", "'"),
                Key("/", "/"),
                Key("\\", "\\"),
                Key("|", "|"),
                Key("~", "~"),
            ),
            // QWERTY row
            listOf(
                Key("Q", "Q"),
                Key("W", "W"),
                Key("E", "E"),
                Key("R", "R"),
                Key("T", "T"),
                Key("Y", "Y"),
                Key("U", "U"),
                Key("I", "I"),
                Key("O", "O"),
                Key("P", "P"),
            ),
            // ASDF row
            listOf(
                Key("A", "A"),
                Key("S", "S"),
                Key("D", "D"),
                Key("F", "F"),
                Key("G", "G"),
                Key("H", "H"),
                Key("J", "J"),
                Key("K", "K"),
                Key("L", "L"),
            ),
            // ZXC row
            listOf(
                Key("Shift", "Shift", width = 1.5f, isModifier = true),
                Key("Z", "Z"),
                Key("X", "X"),
                Key("C", "C"),
                Key("V", "V"),
                Key("B", "B"),
                Key("N", "N"),
                Key("M", "M"),
                Key("Del", "Del", width = 1.5f, isModifier = true),
            ),
            // Bottom row
            listOf(
                Key("?123", "?123", width = 1.5f, isModifier = true),
                Key(",", ","),
                Key(" ", " ", width = 4f),
                Key(".", "."),
                Key("Enter", "Enter", width = 1.5f, isModifier = true),
            )
        )
    )

    val numeric = KeyboardLayout(
        rows = listOf(
            // Number row
            listOf(
                Key("1", "1"),
                Key("2", "2"),
                Key("3", "3"),
                Key("4", "4"),
                Key("5", "5"),
                Key("6", "6"),
                Key("7", "7"),
                Key("8", "8"),
                Key("9", "9"),
                Key("0", "0"),
            ),
            // Symbol row
            listOf(
                Key("@", "@"),
                Key("#", "#"),
                Key("$", "$"),
                Key("%", "%"),
                Key("&", "&"),
                Key("*", "*"),
                Key("-", "-"),
                Key("=", "="),
                Key("(", "("),
                Key(")", ")"),
            ),
            // Extra symbol row
            listOf(
                Key("!", "!"),
                Key("?", "?"),
                Key(":", ":"),
                Key(";", ";"),
                Key("\"", "\""),
                Key("'", "'"),
                Key("_", "_"),
                Key("+", "+"),
                Key("/", "/"),
                Key("\\", "\\"),
            ),
            // Bottom row
            listOf(
                Key("ABC", "ABC", width = 1.5f, isModifier = true),
                Key(",", ","),
                Key(" ", " ", width = 4f),
                Key(".", "."),
                Key("Enter", "Enter", width = 1.5f, isModifier = true),
            )
        )
    )
}
