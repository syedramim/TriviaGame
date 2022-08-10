package com.example.animetrivia

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import kotlin.random.Random

var scoreCounter: Int = 0

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val actionBar = supportActionBar
        val optionOne: Button = findViewById(R.id.b1)
        val optionTwo: Button = findViewById(R.id.b2)
        val optionThree: Button = findViewById(R.id.b3)
        val optionFour: Button = findViewById(R.id.b4)
        val score: TextView = findViewById(R.id.scoreCounter)
        val question: TextView = findViewById(R.id.question)
        val helpButton: Button = findViewById(R.id.helpButton)

        helpButton.setOnClickListener {
            val helpIntent = Intent(this, HelpActivity::class.java)
            startActivity(helpIntent)
        }

        var correctAnswer: String =
            updateQnA(optionOne, optionTwo, optionThree, optionFour, question)


        optionOne.setOnClickListener {
            correctAnswer = buttonClicked(
                optionOne.text == correctAnswer,
                score,
                question,
                optionOne,
                optionTwo,
                optionThree,
                optionFour
            )
        }
        optionTwo.setOnClickListener {
            correctAnswer = buttonClicked(
                optionTwo.text == correctAnswer,
                score,
                question,
                optionOne,
                optionTwo,
                optionThree,
                optionFour
            )
        }
        optionThree.setOnClickListener {
            correctAnswer = buttonClicked(
                optionThree.text == correctAnswer,
                score,
                question,
                optionOne,
                optionTwo,
                optionThree,
                optionFour
            )
        }
        optionFour.setOnClickListener {
            correctAnswer = buttonClicked(
                optionFour.text == correctAnswer,
                score,
                question,
                optionOne,
                optionTwo,
                optionThree,
                optionFour
            )
        }


        actionBar!!.title = "QuizActivity"

        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}


fun buttonClicked(
    correctAnswer: Boolean,
    score: TextView,
    question: TextView,
    button1: Button,
    button2: Button,
    button3: Button,
    button4: Button
): String {
    if (correctAnswer) {
        scoreCounter += 1
        score.text = "Score: $scoreCounter"
    } else {
        scoreCounter = 0
        score.text = "Score: $scoreCounter"
    }
    return updateQnA(button1, button2, button3, button4, question)
}


fun updateQnA(
    button1: Button,
    button2: Button,
    button3: Button,
    button4: Button,
    question: TextView
): String {

    val map = getQnA()
    val correctAnswer: String = map.value[0]

    question.text = map.key
    randomTextToButton(button1, map)
    randomTextToButton(button2, map)
    randomTextToButton(button3, map)
    randomTextToButton(button4, map)

    return correctAnswer
}

fun getQnA(): Map.Entry<String, MutableList<String>> {
    val dictionary = mapOf(
        "What is considered the rarist form of color blindness?" to mutableListOf(
            "Blue",
            "Red",
            "Green",
            "Purple"
        ),
        "Who had hits in the 70s with the songs Lonely Boy and Never Let Her Slip Away?" to mutableListOf(
            "Andrew Gold",
            "Elton John",
            "Leo Sayer",
            "Barry White "
        ),
        "Who was the British professional wrestler Shirley Crabtree better known as?" to mutableListOf(
            "Big Daddy",
            "Giant Haystacks",
            "Kendo Nagasaki",
            "Masambula"
        ),
        "In the year 1900, what were the most popular first names given to boy and girl babies born in the United States?" to mutableListOf(
            "John and Mary",
            "Joseph and Catherine",
            "William and Elizabeth",
            "George and Anne"
        ),
        "How many dice are used in the game of Yahtzee?" to mutableListOf(
            "Five",
            "Four",
            "Six",
            "Eight"
        ),
        "What part of an automobile engine uses lobes to open and close intake and exhaust valves, and allows an air/fuel mixture into the engine?" to mutableListOf(
            "Camshaft",
            "Piston",
            "Drive shaft",
            "Crankshaft"
        ),
        "What is the capital of Jamaica?" to mutableListOf(
            "Kingston",
            "San Juan",
            "Port-au-Prince",
            "Bridgetown"
        ),
        "In which British seaside town was the BBC sitcom Fawlty Towers set?" to mutableListOf(
            "Torquay",
            "Blackpool",
            "Bournemouth",
            "Great Yarmouth"
        ),
        "Neptunes greek name was..." to mutableListOf("Poseidon", "Ares", "Zeus", "Apollo"),
        "In the Gamecube Version of Resident Evil what text document is open on the monitor of the computer in the Visual Data Room?" to mutableListOf(
            "A GDC Document",
            "Text Document on Herbs",
            "Nothing",
            "Document on B.O.Ws"
        ),
        "Which country will host the 2020 Summer Olympics?" to mutableListOf(
            "Japan",
            "China",
            "Australia",
            "Germany"
        ),
        "Which puzzle game was designed by a Russian programmer, featuring Russian buildings and music?" to mutableListOf(
            "Tetris",
            "Jenga",
            "Boulder Dash",
            "Puzzled"
        ),
        "In which year did the First World War begin?" to mutableListOf(
            "1914",
            "1930",
            "1917",
            "1939"
        ),
        "What is the Italian word for tomato?" to mutableListOf(
            "Pomodoro",
            "Aglio",
            "Cipolla",
            "Peperoncino"
        ),
        "If you play the Super Mario RPG and nap in a rented hotel room, you will wake up next to what familiar looking character?" to mutableListOf(
            "Link",
            "Wario",
            "Q*bert",
            "Solid Snake"
        ),
        "Joseph Stalin had a criminal past doing what?" to mutableListOf(
            "Robbing Trains",
            "Murder for Hire",
            "Tax Evasion",
            "Identity Fraud"
        ),
        "In Jeff Waynes Musical Version of War of the Worlds, the chances of anything coming from Mars are..." to mutableListOf(
            "A million to one",
            "A billion to one",
            "A trillion to one",
            "A hundred to one"
        ),
        "Which country was NOT formerly part of Yugoslavia?" to mutableListOf(
            "Albania",
            "Croatia",
            "Serbia",
            "Macedonia"
        ),
        "Who in Pulp Fiction says No, they got the metric system there, they wouldnt know what the fuck a Quarter Pounder is." to mutableListOf(
            "Vincent Vega",
            "Jules Winnfield",
            "Jimmie Dimmick",
            "Butch Coolidge"
        ),
        "Which issue of the Sonic the Hedgehog comic did Scourge the Hedgehog make his first appearance?" to mutableListOf(
            "Sonic the Hedgehog #11",
            "Sonic Universe #32",
            "Sonic the Hedgehog #161",
            "Sonic the Hedgehog #47"
        ),
        "Which actor plays Obi-Wan Kenobi in Star Wars Episodes I-lll?" to mutableListOf(
            "Ewan McGregor",
            "Alec Guinness",
            "Hayden Christensen",
            "Liam Neeson"
        ),
        "What was the name of the first MMORPG to popularize the genre?" to mutableListOf(
            "Ultima Online",
            "World of Warcraft",
            "Meridian 59",
            "Guild Wars"
        ),
        "Which male player won the gold medal of table tennis singles in 2016 Olympics Games?" to mutableListOf(
            "Ma Long (China)",
            "Zhang Jike (China)",
            "Jun Mizutani (Japan)",
            "Vladimir Samsonov (Belarus)"
        ),
        "What is the busiest port in Europe?" to mutableListOf(
            "Port of Rotterdam",
            "Port of Antwerp",
            "Port of Hamburg",
            "Port of Amsterdam"
        ),
        "What was the original name of  Minecraft?" to mutableListOf(
            "Cave Game",
            "Minecraft: Order of the Stone",
            "Dig And Build",
            "Infiniminer"
        ),
        "In the television show Lazy Town, who is the actor of Robbie Rotten?" to mutableListOf(
            "Stefn Stefnsson",
            "Adam Sandler",
            "Magns Scheving",
            "Stephen Carl"
        ),
        "Krusty is the guild master of which guild in Log Horizon?" to mutableListOf(
            "D. D. D",
            "Silver Sword",
            "West Wind Brigade",
            "Oceanic Systems (Marine Agency)"
        ),
        "What was the last message of the Dolphins in The Hitchhikers Guide to the Galaxy?" to mutableListOf(
            "So long, and thanks for all the fish.",
            "The answer is 42.",
            "Land of the brave.",
            "Goodbye cruel world!"
        ),
        "In Telltale Games The Walking Dead: Season One what is the name of Clementines father?" to mutableListOf(
            "Ed",
            "Charles",
            "Lee",
            "Walter"
        ),
        "Which Sanrio character was introduced in 1996?" to mutableListOf(
            "Pompompurin",
            "My Melody",
            "Badtz-Maru",
            "Kerropi"
        ),
        "What is the most common climbing route for the second highest mountain in the world, K2?" to mutableListOf(
            "Abruzzi Spur",
            "Magic Line",
            "Cesen Route",
            "Polish Line"
        ),
        "What is the real name of Warhead in the Sega Genesis game Vectorman?" to mutableListOf(
            "Raster",
            "Peacehead",
            "Vectorkid",
            "Bitmap"
        ),
        "Who led the Communist Revolution of Russia?" to mutableListOf(
            "Vladimir Lenin",
            "Joseph Stalin",
            "Vladimir Putin",
            "Mikhail Gorbachev"
        ),
        "In Need for Speed III: Hot Pursuit, what is the name of the fictional car?" to mutableListOf(
            "El Nio",
            "Rpido",
            "gil",
            "La Nia"
        ),
        "What is the Zodiac symbol for Gemini?" to mutableListOf(
            "Twins",
            "Fish",
            "Scales",
            "Maiden"
        ),
        "Which noble gas has the lowest atomic number?" to mutableListOf(
            "Helium",
            "Neon",
            "Argon",
            "Krypton"
        ),
        "When someone is cowardly, they are said to have what color belly?" to mutableListOf(
            "Yellow",
            "Green",
            "Red",
            "Blue"
        ),
        "Which of these cities is NOT in England?" to mutableListOf(
            "Edinburgh",
            "Oxford",
            "Manchester",
            "Southampton"
        ),
        "In Terry Pratchetts Discworld novel Wyrd Sisters, which of these are not one of the three main witches?" to mutableListOf(
            "Winny Hathersham",
            "Granny Weatherwax",
            "Nanny Ogg",
            "Magrat Garlick"
        ),
        "Which of these choices is not one of the phases of mitosis?" to mutableListOf(
            "Diplophase",
            "Metaphase",
            "Anaphase",
            "Telophase"
        ),
        "Which of the following is not a megadiverse country - one that harbors a high number of the earths endemic species?" to mutableListOf(
            "Thailand",
            "Peru",
            "Mexico",
            "South Africa"
        ),
        "In physics, conservation of energy and conservation of momentum are both consequences of which of the following?" to mutableListOf(
            "Noethers Theorem",
            "Bells Theorem",
            "Wicks Theorem",
            "Carnots Theorem"
        ),
        "The killer in the 1981 film My Bloody Valentine wears what sort of outfit?" to mutableListOf(
            "Mining gear",
            "Clown costume",
            "Santa suit",
            "Police uniform"
        ),
        "In Two and a Half Men, what is Alan Harpers sons name?" to mutableListOf(
            "Jake",
            "John",
            "Jeremy",
            "James"
        ),
        "With which team did Michael Schumacher make his Formula One debut at the 1991 Belgian Grand Prix?" to mutableListOf(
            "Jordan",
            "Benetton",
            "Ferrari",
            "Mercedes"
        ),
        "King Henry VIII was the second monarch of which European royal house?" to mutableListOf(
            "Tudor",
            "York",
            "Stuart",
            "Lancaster"
        ),
        "In what year did Neil Armstrong and Buzz Aldrin land on the moon?" to mutableListOf(
            "1969",
            "1965",
            "1966",
            "1973"
        ),
        "Area 51 is located in which US state?" to mutableListOf(
            "Nevada",
            "Arizona",
            "New Mexico",
            "Utah"
        ),
        "Which essential condiment is also known as Japanese horseradish?" to mutableListOf(
            "Wasabi ",
            "Mentsuyu",
            "Karashi",
            "Ponzu"
        ),
        "Which of these levels does NOT appear in the console/PC versions of the game Sonic Generations?" to mutableListOf(
            "Mushroom Hill",
            "City Escape",
            "Planet Wisp",
            "Sky Sanctuary"
        ),
        "What is the name of the Jewish New Year?" to mutableListOf(
            "Rosh Hashanah",
            "Elul",
            "New Year",
            "Succoss"
        ),
        "Which of these companies does NOT manufacture automobiles?" to mutableListOf(
            "Ducati",
            "Nissan",
            "GMC",
            "Fiat"
        ),
        "In Inuyasha, what are the heros are looking to collect?" to mutableListOf(
            "Jewel Shards",
            "Dragon Balls",
            "Rave Stones",
            "Sacred Stones"
        ),
        "How many people are in the U.S. House of Representatives?" to mutableListOf(
            "435",
            "260",
            "415",
            "50"
        ),
        "On average, Americans consume 100 pounds of what per second?" to mutableListOf(
            "Chocolate",
            "Potatoes",
            "Donuts",
            "Cocaine"
        ),
        "Which movie of film director Stanley Kubrick is known to be an adaptation of a Stephen King novel?" to mutableListOf(
            "The Shining",
            "2001: A Space Odyssey",
            " Dr. Strangelove ",
            "Eyes Wide Shut"
        ),
        "Which car company is the only Japanese company which won the 24 Hours of Le Mans?" to mutableListOf(
            "Mazda",
            "Toyota",
            "Subaru",
            "Nissan"
        ),
        "Which of the following is not a playable race in the MMORPG Guild Wars 2? " to mutableListOf(
            "Tengu",
            "Sylvari",
            "Charr",
            "Asura "
        ),
        "Which of these Roman gods doesnt have a counterpart in Greek mythology?" to mutableListOf(
            "Janus",
            "Vulcan",
            "Juno",
            "Mars"
        ),
        "What was the first PlayStation game to require the use of the DualShock controller?" to mutableListOf(
            "Ape Escape",
            "Metal Gear",
            "Tekken ",
            "Tomba 2!"
        ),
        "In Naruto: Shippuden, which of the following elements is a Kekkei Tōta?" to mutableListOf(
            "Particle Style",
            "Any Doujutsu",
            "Shadow Style",
            "Ice Style"
        ),
        "Who invented the Flying Shuttle in 1738; one of the key developments in the industrialization of weaving?" to mutableListOf(
            "John Kay",
            "James Hargreaves",
            "Richard Arkwright",
            "John Deere"
        ),
        "Which Italian footballer told Neuer where hes putting his shot and dragging it wide, during the match Italy-Germany, UEFA EURO 2016?" to mutableListOf(
            "Pelle",
            "Insigne",
            "Barzagli",
            "Giaccherini"
        ),
        "Who voices the infamous Citadel Station A.I known as S.H.O.D.A.N, in the System Shock games?" to mutableListOf(
            "Terri Brosius",
            " Jennifer Hale",
            "Jenn Taylor",
            "Lori Alan"
        ),
        "What is the smallest country in South America by area?" to mutableListOf(
            "Suriname",
            "Brazil",
            "Uruguay",
            "Chile"
        ),
        "In the Panic! At the Discos song Nothern Downpour, which lyric follows I know the worlds a broken bone." to mutableListOf(
            "So melt your headaches call it home",
            "So sing your song until youre home",
            "So let them know theyre on their own",
            "So start a fire in their cold stone"
        ),
        "In what year were screenshots added to Steam?" to mutableListOf(
            "2011",
            "2010",
            "2008",
            "2009"
        ),
        "Phil Fish was the designer of which game?" to mutableListOf(
            "Fez",
            "Super Meat Boy",
            "Hotline Miami",
            "FTL"
        ),
        "How much weight did Chris Pratt lose for his role as Star-Lord in Guardians of the Galaxy?" to mutableListOf(
            "60 lbs",
            "30 lbs",
            "50 lbs",
            "70 lbs"
        ),
        "The characters of Log Horizon are trapped in what game?" to mutableListOf(
            "Elder Tale",
            "Sword Art Online",
            "Tower Unite",
            "Yggdrasil"
        ),
        "In the Dragon Ball franchise, what is the name of Gokus brother?" to mutableListOf(
            "Raditz",
            "Gohan",
            "Vegeta",
            "Bardock"
        ),
        "Which of these is not a wonder weapon in Call Of Duty: Zombies?" to mutableListOf(
            "R115 Resonator",
            "GKZ-45 Mk3",
            "Ray Gun",
            "Scavenger"
        ),
        "Which of these voices wasnt a choice for the House AI in The Simpsons Treehouse of Horror short, House of Whacks?" to mutableListOf(
            "George Clooney",
            "Matthew Perry",
            "Dennis Miller",
            "Pierce Brosnan"
        ),
        "The minigun was designed in 1960 by which manufacturer." to mutableListOf(
            "General Electric",
            "Colt Firearms",
            "Heckler  Koch",
            "Sig Sauer"
        ),
        "What is the name of the protagonists first Persona in Persona 5?" to mutableListOf(
            "Arsene",
            "Mara",
            "Izanagi",
            "Sandaphlon"
        ),
        "In which country is Tallinn located?" to mutableListOf(
            "Estonia",
            "Finland",
            "Sweden",
            "Poland"
        ),
        "The stuffed tiger in Calvin and Hobbes is named after what philosopher?" to mutableListOf(
            "Thomas Hobbes",
            "David Hobbes",
            "John Hobbes",
            "Nathaniel Hobbes"
        ),
        "When was the Grand Patriotic War in the USSR concluded?" to mutableListOf(
            "May 9th, 1945",
            "September 2nd, 1945",
            "August 9th, 1945",
            "December 11th, 1945"
        ),
        "Toussaint Louverture led a successful slave revolt in which country?" to mutableListOf(
            "Haiti",
            "France",
            "Cuba",
            "United States"
        ),
        "What nuts are used in the production of marzipan?" to mutableListOf(
            "Almonds",
            "Peanuts",
            "Walnuts",
            "Pistachios"
        ),
        "In Battle Cats, what is Moneko / MISS Monekos critical percentage rate?" to mutableListOf(
            "15%",
            "20%",
            "10%",
            "25%"
        ),
        "What does the S in the RSA encryption algorithm stand for?" to mutableListOf(
            "Shamir",
            "Secure",
            "Schottky",
            "Stable"
        ),
        "What is a Tetris piece called?" to mutableListOf(
            "Tetromino",
            "Tetratile",
            "Tetris",
            "Tetripiece"
        ),
        "What airline was the owner of the plane that crashed off the coast of Nova Scotia in 1998?" to mutableListOf(
            "Swiss Air",
            "Air France",
            "British Airways",
            "TWA"
        ),
        "The board game Monopoly is a variation of what board game?" to mutableListOf(
            "The Landlords Game",
            "Territorial Dispute",
            "Property Feud",
            "The Monopolists Game"
        ),
        "In the film Interstellar, how long did they spend on Millers planet?" to mutableListOf(
            "23 years, 4 months, and 8 days",
            "15 years, 2 months, and 15 days",
            "10 months and 6 days",
            "26 years, 4 months, and 10 days"
        ),
        "Which of these stars is the largest?" to mutableListOf(
            "UY Scuti",
            "VY Canis Majoris",
            "Betelgeuse",
            "RW Cephei"
        ),
        "Who wrote the novel Fear And Loathing In Las Vegas?" to mutableListOf(
            "Hunter S. Thompson",
            "F. Scott Fitzgerald",
            "Henry Miller",
            "William S. Burroughs"
        ),
        "The mountainous Khyber Pass connects which of the two following countries?" to mutableListOf(
            "Afghanistan and Pakistan",
            "India and Nepal",
            "Pakistan and India",
            "Tajikistan and Kyrgyzstan"
        ),
        "Which psychological term refers to the stress of holding contrasting beliefs?" to mutableListOf(
            "Cognitive Dissonance",
            "Flip-Flop Syndrome",
            "Split-Brain",
            "Blind Sight"
        ),
        "In Portal 2, the iconic character GLaDOS is turned into:" to mutableListOf(
            "A potato",
            "A tomato",
            "A lemon",
            "An apple"
        ),
        "Which Toronto landmark was featured on the cover art of Canadian rapper Drakes 2016 album Views?" to mutableListOf(
            "CN Tower",
            "Allan Gardens",
            "Union Station",
            "Prince of Wales Theatre"
        ),
        "In the 1994 movie Speed, what is the minimum speed the bus must go to prevent to bomb from exploding?" to mutableListOf(
            "50 mph",
            "60 mph",
            "40 mph",
            "70 mph"
        ),
        "In World of Warcraft lore, who organized the creation of the Paladins?" to mutableListOf(
            "Alonsus Faol",
            "Uther the Lightbringer",
            "Alexandros Mograine",
            "Sargeras, The Daemon Lord"
        ),
        "Which company made the Japanese RPG Dragon Quest?" to mutableListOf(
            "Square Enix",
            "Capcom",
            "Konami",
            "Blizzard"
        ),
        "What is the real name of Canadian electronic music producer deadmau5?" to mutableListOf(
            "Joel Zimmerman",
            "Sonny Moore",
            "Adam Richard Wiles",
            "Thomas Wesley Pentz"
        ),
        "According to Sherlock Holmes, If you eliminate the impossible, whatever remains, however improbable, must be the..." to mutableListOf(
            "Truth",
            "Answer",
            "Cause",
            "Source"
        ),
        "What city is known as the Rose Capital of the World?" to mutableListOf(
            "Tyler, Texas",
            "San Diego, California",
            "Miami, Florida",
            "Anaheim, California"
        ),
        "Which of the following is not a faction in Tom Clancys The Division?" to mutableListOf(
            "CDC",
            "Cleaners",
            "Last Man Batallion",
            "Rikers"
        ),
        "Which country is the home of the largest Japanese population outside of Japan?" to mutableListOf(
            "Brazil",
            "China",
            "Russia",
            "The United States"
        ),
        "Which of the following snipers has the highest amount of confirmed kills?" to mutableListOf(
            "Simo Hyh",
            "Chris Kyle",
            "Vasily Zaytsev",
            "Craig Harrison"
        ),
        "De Eemhof, Port Zelande and Het Heijderbos are holiday villas owned by what company?" to mutableListOf(
            "Center Parcs",
            "Yelloh Village",
            "Keycamp",
            "Villa Plus"
        ),
        "According to Overwatchs lore, who was once a member of the Deadlock Gang?" to mutableListOf(
            "McCree",
            "Roadhog",
            "Soldier 76",
            "Junkrat"
        ),
        "Which of these operators from Tom Clancys Rainbow Six Siege has the ability to damage reinforced walls?" to mutableListOf(
            "Jordan Thermite Trace",
            "Eliza Ash Cohen",
            "Seamus Sledge Cowden",
            "Dominic Bandit Brunsmeier"
        ),
        "In the TV Show Donkey Kong Country, which episode did the song Eddie, Let Me Go Back To My Home play in?" to mutableListOf(
            "Its a Wonderful Life",
            "Message In A Bottle Show",
            "To The Moon Baboon",
            "Ape-Nesia"
        ),
        "Which of the following is not a character in the Street Fighter series?" to mutableListOf(
            "Mai Shiranui",
            "Laura Matsuda",
            "Sakura Kasugano",
            "Ibuki"
        ),
        "Which of the following games in the The Legend of Zelda franchise was released in North America before Japan?" to mutableListOf(
            "The Legend of Zelda: Twilight Princess",
            "Zelda II: The Adventure of Link",
            "The Legend of Zelda: Four Swords Adventures",
            "The Legend of Zelda: Majoras Mask"
        ),
        "What was the first Android version specifically optimized for tablets?" to mutableListOf(
            "Honeycomb",
            "Eclair",
            "Froyo",
            "Marshmellow"
        ),
        "In Star Trek, what sauce is commonly used by Klingons on bregit lung?" to mutableListOf(
            "Grapok sauce",
            "Gazorpazorp pudding",
            "Sweet chili sauce",
            "Grapork sauce"
        ),
        "In Starbound, according to the asset files, the description of the Erchius Ghost is the same as which other assets?" to mutableListOf(
            "Spookit",
            "Petricub",
            "Trictus",
            "Pyromantle"
        ),
        "In Call Of Duty: Zombies, what does the game traditionally reward you for completing a boss round?" to mutableListOf(
            "Max Ammo",
            "A Pack-A-Punched gun",
            "Death Machine",
            "Monkey Bombs"
        ),
        "What are human nails made of?" to mutableListOf("Keratin", "Bone", "Chitin", "Calcium"),
        "From which album is the Gorillaz song, On Melancholy Hill featured in?" to mutableListOf(
            "Plastic Beach",
            "Demon Days",
            "Humanz",
            "The Fall"
        ),
        "What was the development code name for the Urzas Destiny expansion for Magic: The Gathering, released in 1999?" to mutableListOf(
            "Chimichanga",
            "Burrito",
            "Taquito",
            "Enchilada"
        ),
        "In Finding Nemo, what was the name of Nemos mom?" to mutableListOf(
            "Coral",
            "Sandy",
            "Pearl",
            "Shelly"
        ),
        "In what year was the card game Magic: the Gathering first introduced?" to mutableListOf(
            "1993",
            "1987",
            "1998",
            "2003"
        ),
        "Which painting was not made by Vincent Van Gogh?" to mutableListOf(
            "The Ninth Wave",
            "Caf Terrace at Night",
            "Bedroom In Arles",
            "Starry Night"
        ),
        "The Flag of the European Union has how many stars on it?" to mutableListOf(
            "12",
            "10",
            "14",
            "16"
        ),
        "What was the name of the planned invasion of Japan towards the end of World War II?" to mutableListOf(
            "Operation Downfall",
            "Operation Boarding Party",
            "Operation Ironclad",
            "Operation Aflame"
        ),
        "What is the name of the alligator in Wheres My Water?" to mutableListOf(
            "Swampy",
            "Cranky",
            "Crocky",
            "Justice"
        ),
        "In the show Dragonball Z, what is the name of Cells most powerful attack?" to mutableListOf(
            "Solar Kamehameha",
            "Super Kamehameha",
            "Cell Kamehameha",
            "Android Kamehameha"
        ),
        "Which of these cities does NOT have a United States Minting location?" to mutableListOf(
            "St. Louis, MO",
            "San Fransisco, CA",
            "Philidelphia, PA",
            "West Point, NY"
        ),
        "What is the smallest number that can be expressed as the sum of two positive cubes in two different ways?" to mutableListOf(
            "1729",
            "91",
            "561",
            "4104"
        ),
        "What was the name of the chemical that was dropped on Vietnam during the Vietnam war?" to mutableListOf(
            "Agent Orange",
            "Phosgene",
            "Mustard Gas",
            "Hydrogen Cyanide"
        ),
        "In the Nintendo Game Splatoon 2, what is Marinas screen name?" to mutableListOf(
            "DJ_Hyperfresh",
            "MC.princess",
            "Kidnotsquid123",
            "I30ffTh3H00k"
        ),
        "In Black Hammer, what city did the heroes save from the Anti-God?" to mutableListOf(
            "Spiral City",
            "Mega-City One",
            "Rockwood",
            "Star City"
        ),
        "In the TV series Person of Interest, who plays the character Harold Finch?" to mutableListOf(
            "Michael Emerson",
            "Jim Caviezel",
            "Taraji P. Henson",
            "Kevin Chapman"
        ),
        "Which time signature is commonly known as Cut Time?" to mutableListOf(
            "2/2",
            "4/4",
            "6/8",
            "3/4"
        ),
        "What is the largest city and commercial capital of Sri Lanka?" to mutableListOf(
            "Colombo",
            "Moratuwa",
            "Negombo",
            "Kandy"
        ),
        "Which anime heavily features music from the genre Eurobeat?" to mutableListOf(
            "Initial D",
            "Wangan Midnight",
            "Kino no Tabi",
            "Cowboy Bebop"
        ),
        "In Undertale, how many main endings are there?" to mutableListOf("3", "2", "5", "13"),
        "What is the capital of Australia?" to mutableListOf(
            "Canberra",
            "Sydney",
            "Melbourne",
            "Brisbane"
        ),
        "When was YouTube founded?" to mutableListOf(
            "February 14, 2005",
            "May 22, 2004",
            "September 12, 2005",
            "July 19, 2009"
        ),
        "When was Tesla founded?" to mutableListOf("2003", "2008", "2005", "2007"),
        "How many Harry Potter books are there?" to mutableListOf("7", "8", "5", "6"),
        "What was the name of Ross pet monkey on Friends?" to mutableListOf(
            "Marcel",
            "Jojo",
            "George",
            "Champ"
        ),
        "Which of the following is NOT a real element?" to mutableListOf(
            "Vitrainium",
            "Praseodymium",
            "Hassium",
            "Lutetium"
        ),
        "What device allows Tracer to manipulate her own time in the game Overwatch?" to mutableListOf(
            "Chronal Accelerator",
            "B.L.I.N.K",
            "Spacial Displacement Manipulator",
            "TMD (Time Manipulation Device)"
        ),
        "What was the religion of famous singer Freddie Mercury?" to mutableListOf(
            "Zoroastrianism",
            "Paganism",
            "Ashurism",
            "Judaism"
        ),
        "What year did the Boxing Day earthquake  tsunami occur in the Indian Ocean?" to mutableListOf(
            "2004",
            "2006",
            "2008",
            "2002"
        ),
        "What year was the game Dishonored released?" to mutableListOf(
            "2012",
            "2011",
            "2008",
            "2013"
        ),
        "In the original DOOM games, which of the following demons is a recolor of the Baron of Hell, but is weaker than the Baron?" to mutableListOf(
            "Hell Knight",
            "Mancubus",
            "Pinky",
            "Arch-Vile"
        ),
        "Which of the following games in the Anno series introduced the Eco Balance gameplay mechanic?" to mutableListOf(
            "Anno 2070",
            "Anno 1404",
            "Anno 2205",
            " Anno 1701"
        ),
        "In Mario  Sonic at the Olympic Games, characters are split into how many types?" to mutableListOf(
            "4",
            "6",
            "5",
            "3"
        ),
        "In the 2016 Global Peace Index poll, out of 163 countries, what was the United States of America ranked?" to mutableListOf(
            "103",
            "10",
            "59",
            "79"
        ),
        "In Hunter x Hunter, which of the following is NOT a type of Nen aura?" to mutableListOf(
            "Restoration",
            "Emission",
            "Transmutation",
            "Specialization"
        ),
        "Leonardo Di Caprio won his first Best Actor Oscar for his performance in which film?" to mutableListOf(
            "The Revenant",
            "The Wolf Of Wall Street",
            "Shutter Island",
            "Inception"
        ),
        "Which desert is the only desert in the world where the Saguaro cactus grows indigenously?" to mutableListOf(
            "The Sonoran Desert",
            "The Gobi Desert",
            "The Yuma Desert",
            "The Arabian Desert"
        ),
        "Which of these characters wasnt a villian in Club Penguin?" to mutableListOf(
            "The Director",
            "Herbert P. Bear",
            "Tusk",
            "Ultimate Proto-Bot 10000"
        ),
        "Which country gifted the Statue of Liberty to the United States of America?" to mutableListOf(
            "France",
            "Spain",
            "England",
            "Germany"
        ),
        "What vulnerability ranked #1 on the OWASP Top 10 in 2013?" to mutableListOf(
            "Injection ",
            "Broken Authentication",
            "Cross-Site Scripting",
            "Insecure Direct Object References"
        ),
        "Which candy is NOT made by Mars?" to mutableListOf(
            "Almond Joy",
            "MMs",
            "Twix",
            "Snickers"
        ),
        "Which director directed the movie Pans Labyrinth?" to mutableListOf(
            "Guillermo Del Toro",
            "Alfonso Cuarn",
            "Alejandro Gonzlez Irritu",
            " Alejandro Jodorowsky"
        ),
        "Who performed the guitar solo on Michael Jacksons hit Beat It?" to mutableListOf(
            "Eddie Van Halen",
            "Steve Vai",
            "Kirk Hammett",
            "Zakk Wylde"
        ),
        "Why was The Green Monster at Fenway Park was originally built?" to mutableListOf(
            "To prevent viewing games from outside the park.",
            "To make getting home runs harder.",
            "To display advertisements.",
            "To provide extra seating."
        ),
        "What is the half-life of Uranium-235?" to mutableListOf(
            "703,800,000 years",
            "4,300,400,000 years",
            "1,260,900,000 years",
            "Uranium-235 is a stable isotope"
        ),
        "In the Overlord Anime who was Cocytus made by?" to mutableListOf(
            "Warrior Takemikazuchi",
            "Peroroncino",
            "Ulbert Alain Odle",
            "Bukubukuchagama"
        ),
        "Which former Coronation Street actress was once a hostess on the British Game Show Double Your Money?" to mutableListOf(
            "Amanda Barrie",
            "Sue Nicholls",
            "Violet Carson",
            "Jean Alexander"
        ),
        "What was the name given to Japanese military dictators who ruled the country through the 12th and 19th Century?" to mutableListOf(
            "Shogun",
            "Ninja",
            "Samurai",
            "Shinobi"
        ),
        "In the MMO RPG Realm of the Mad God, what dungeon is widely considered to be the most difficult?" to mutableListOf(
            "The Shatters",
            "Snake Pit",
            "The Tomb of the Acients",
            "The Puppet Masters Theater"
        ),
        "How many premier league trophies did Sir Alex Ferguson win during his time at Manchester United?" to mutableListOf(
            "13",
            "11",
            "20",
            "22"
        ),
        "What is the real name of the Master Of Magnetism Magneto?" to mutableListOf(
            "Max Eisenhardt",
            "Charles Xavier",
            "Pietro Maximoff",
            "Johann Schmidt"
        ),
        "When did O, Canada officially become the national anthem?" to mutableListOf(
            "1980",
            "1950",
            "1920",
            "1880"
        ),
        "What name is the main character Chihiro given in the 2001 movie Spirited Away?" to mutableListOf(
            "Sen (Thousand)",
            "Hyaku (Hundred)",
            "Ichiman (Ten thousand)",
            "Juu (Ten)"
        ),
        "The Bohemian Revolt (1618-1620) started after Protestants in Prague did what to their Catholic Lords Regents?" to mutableListOf(
            "Threw them out of a window",
            "Insulted their mothers",
            "Locked them in stockades",
            "Hung them."
        ),
        "What is the birth name of Michael Keaton?" to mutableListOf(
            "Michael Douglas",
            "Michael Fox",
            "Michael Richards",
            "Michael Kane"
        ),
        "What are the three starter Pokemon available in Pokemon Black and White?" to mutableListOf(
            "Snivy, Tepig, Oshawott",
            "Snivy, Fennekin, Froakie",
            "Chespin, Tepig, Froakie",
            "Chespin, Fennekin, Oshawott"
        ),
        "What tool lends its name to a last-stone advantage in an end in Curling?" to mutableListOf(
            "Hammer",
            "Wrench",
            "Drill",
            "Screwdriver"
        ),
        "Who is the main antagonist in the Portal franchise?" to mutableListOf(
            "GLaDOS",
            "Chell",
            "Wheatley",
            "Rick"
        ),
        "On a London Underground map, what colour is the Circle Line?" to mutableListOf(
            "Yellow",
            "Red",
            "Blue",
            "Green"
        ),
        "In the The Hobbit, who kills Smaug?" to mutableListOf(
            "Bard",
            "Bilbo Baggins",
            "Gandalf the Grey",
            "Frodo"
        ),
        "What is the code name for the mobile operating system Android 7.0?" to mutableListOf(
            "Nougat",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "Marshmallow"
        ),
        "What year was the Disney film A Goofy Movie released?" to mutableListOf(
            "1995",
            "1999",
            "2001",
            "1997"
        ),
        "What is the unit of currency in Laos?" to mutableListOf("Kip", "Ruble", "Konra", "Dollar"),
        "Which of these is NOT a car model produced by Malaysian car manufacturer Proton?" to mutableListOf(
            "Kelisa",
            "Saga",
            "Perdana",
            "Inspira"
        ),
        "What is the scientific name of the cheetah?" to mutableListOf(
            "Acinonyx jubatus",
            "Panthera onca",
            "Lynx rufus",
            "Felis catus"
        ),
        "Johnny Cash did a cover of this song written by lead singer of Nine Inch Nails, Trent Reznor." to mutableListOf(
            "Hurt",
            "Closer",
            "A Warm Place",
            "Big Man with a Gun"
        ),
        "Which is the most abundant element in the universe?" to mutableListOf(
            "Hydrogen",
            "Helium",
            "Lithium",
            "Oxygen"
        ),
        "In the anime Seven Deadly Sins what is the name of one of the sins?" to mutableListOf(
            "Diane",
            "Sakura",
            "Ayano",
            "Sheska"
        ),
        "In DuckTales, what is the name of the city where all the main characters live?" to mutableListOf(
            "Duckburg",
            "Duckwing",
            "Tailspin",
            "Wingford"
        ),
        "In Kendrick Lamars 2012 album, Good Kid, M.A.A.D City, the albums story takes place in which city?" to mutableListOf(
            "Compton",
            "Detroit",
            "New York",
            "Baltimore"
        ),
        "Which computer language would you associate Django framework with?" to mutableListOf(
            "Python",
            "C#",
            "C++",
            "Java"
        ),
        "What is the Polish city known to Germans as Danzig?" to mutableListOf(
            "Gdańsk",
            "Warsaw",
            "Zakopane",
            "Poznań"
        ),
        "Where was Kanye West born?" to mutableListOf(
            "Atlanta, Georgia",
            "Chicago, Illinois",
            "Los Angeles, California",
            "Detroit, Michigan"
        ),
        "What year did the James Cameron film Titanic come out in theaters?" to mutableListOf(
            "1997",
            "1996",
            "1998",
            "1999"
        ),
        "When was Gangnam Style uploaded to YouTube?" to mutableListOf(
            "2012",
            "2013",
            "2014",
            "2011"
        ),
        "Which car manufacturer created the Aventador?" to mutableListOf(
            "Lamborghini",
            "Ferrari",
            "Pagani",
            "Bugatti"
        ),
        "In SpongeBob SquarePants, what is the name of Sandy Cheeks place of residence?" to mutableListOf(
            "Sandys Treedome",
            "The Dome",
            "Sandys Bubble",
            "Auquatic Reseach Centre"
        ),
        "In the Jurassic Park universe, when did Jurassic Park: San Diego begin construction?" to mutableListOf(
            "1985",
            "1988",
            "1986",
            "1993"
        ),
        "The dish Fugu, is made from what family of fish?" to mutableListOf(
            "Pufferfish",
            "Bass",
            "Salmon",
            "Mackerel"
        ),
        "In Margaret Atwoods The Handmaids Tale, what is Offreds real name is implied to be?" to mutableListOf(
            "June",
            "August",
            "April",
            "May"
        ),
        "Who succeeded Joseph Stalin as General Secretary of the Communist Party of the Soviet Union?" to mutableListOf(
            "Nikita Khrushchev",
            "Leonid Brezhnev",
            "Mikhail Gorbachev",
            "Boris Yeltsin"
        ),
        "Which Japanese music group was formed to produce theme music for the anime Guilty Crown?" to mutableListOf(
            "Egoist",
            "Goose house",
            "Babymetal",
            "Garnidelia"
        ),
        "7475 is a 1993 single from the album Ring by what American band?" to mutableListOf(
            "The Connells",
            "R.E.M.",
            "The Ocean Blue",
            "The Bangles"
        ),
        "How many states are in Australia?" to mutableListOf("6", "7", "8", "5"),
        "What physics principle relates the net electric flux out of a closed surface to the charge enclosed by that surface?" to mutableListOf(
            "Gausss Law",
            "Faradays Law",
            "Amperes Law",
            "Biot-Savart Law"
        ),
        "What is the name of Manchester Uniteds home stadium?" to mutableListOf(
            "Old Trafford",
            "Anfield",
            "City of Manchester Stadium",
            "St James Park"
        ),
        "What was Britney Spears debut single?" to mutableListOf(
            "...Baby One More Time",
            "Oops!... I Did It Again",
            "(You Drive Me) Crazy",
            "Toxic"
        ),
        "What Led Zeppelin album contains Stairway to Heaven?" to mutableListOf(
            "Led Zeppelin IV",
            "Houses of the Holy",
            "Physical Graffiti",
            "Led Zeppelin III"
        ),
        "Where is Hadrians Wall located?" to mutableListOf(
            "Carlisle, England",
            "Rome, Italy",
            "Alexandria, Egypt",
            "Dublin, Ireland"
        ),
        "What is Hypernatremia?" to mutableListOf(
            "Increase in blood sodium",
            "Decrease in blood potassium",
            "Increase in blood glucose",
            "Decrease in blood iron"
        ),
        "About how much money did it cost for Tommy Wiseau to make his masterpiece The Room (2003)?" to mutableListOf(
            "$6 Million",
            "$20,000",
            "$1 Million",
            "$10 Million"
        ),
        "In the Mad Max franchise, what type of car is the Pursuit Special driven by Max?" to mutableListOf(
            "Ford Falcon",
            "Holden Monaro",
            "Chrysler Valiant Charger",
            "Pontiac Firebird"
        ),
        "When was the original Star Wars: Battlefront II released?" to mutableListOf(
            "October 31, 2005",
            "December 18, 2004",
            "November 21, 2006",
            "September 9, 2007"
        ),
        "What does CPU stand for?" to mutableListOf(
            "Central Processing Unit",
            "Central Process Unit",
            "Computer Personal Unit",
            "Central Processor Unit"
        ),
        "Which of the following is the worlds best-selling book?" to mutableListOf(
            "The Lord of the Rings",
            "The Little Prince",
            "Harry Potter and the Philosophers Stone",
            "The Da Vinci Code"
        ),
        "Rolex is a company that specializes in what type of product?" to mutableListOf(
            "Watches",
            "Cars",
            "Computers",
            "Sports equipment"
        ),
        "The Red Hot Chili Pepper song Give It Away is from what album?" to mutableListOf(
            "Blood Sugar Sex Magik",
            "One Hot Minute",
            "By the Way",
            "Stadium Arcadium"
        ),
        "Who was the 40th President of the USA?" to mutableListOf(
            "Ronald Reagan",
            "Jimmy Carter",
            "Bill Clinton",
            "Richard Nixon"
        ),
        "Which of the following passenger jets is the longest?" to mutableListOf(
            "Boeing 747-8",
            "Airbus A350-1000",
            "Airbus A330-200",
            "Boeing 787-10"
        ),
        "What does TIE stand for in reference to the TIE Fighter in Star Wars?" to mutableListOf(
            "Twin Ion Engine",
            "Twin Iron Engine",
            "Twin Intercepter Engine",
            "Twin Inception Engine"
        ),
        "What is the setting of the show Parks and Recreation?" to mutableListOf(
            "Pawnee, Indiana",
            "Eagleton, Indiana",
            "Pasadena, California",
            "London, England"
        ),
        "In Sonic the Hedgehog 3 for the Sega Genesis, what is the color of the second Chaos Emerald you can get from Special Stages?" to mutableListOf(
            "Orange",
            "Blue",
            "Green",
            "Magenta"
        ),
        "How many Chaos Emeralds are there in the Sonic the Hedgehog universe?" to mutableListOf(
            "7",
            "6",
            "8",
            "14"
        ),
        "How many zombies need to be killed to get the Zombie Genocider achievement in Dead Rising (2006)?" to mutableListOf(
            "53,594",
            "53,593",
            "53,595",
            "53,596"
        ),
        "In Dota 2, Wraith King was previously known as..." to mutableListOf(
            "Skeleton King",
            "Reaper King",
            "Skull King",
            "Hell King"
        ),
        "What is the name of French electronic music producer Madeons 2015 debut studio album?" to mutableListOf(
            "Adventure",
            "The City",
            "Icarus",
            "Pop Culture"
        ),
        "What is the nickname for the US state Delaware?" to mutableListOf(
            "The First State",
            "The Fiftieth State",
            "The Second State",
            "The Sixteenth State"
        ),
        "The human heart has how many chambers?" to mutableListOf("4", "2", "6", "3"),
        "What video game sparked controversy because of its hidden Hot Coffee minigame?" to mutableListOf(
            "Grand Theft Auto: San Andreas",
            "Grand Theft Auto: Vice City",
            "Hitman: Blood Money",
            "Cooking Mama"
        ),
        "In the anime Black Butler, who is betrothed to be married to Ciel Phantomhive?" to mutableListOf(
            "Elizabeth Midford",
            "Rachel Phantomhive",
            "Alexis Leon Midford",
            "Angelina Dalles"
        ),
        "What is the surname of the character Daryl in AMCs show The Walking Dead?" to mutableListOf(
            "Dixon",
            "Grimes",
            "Dickinson",
            "Dicketson"
        ),
        "Which car tire manufacturer is famous for its P Zero line?" to mutableListOf(
            "Pirelli",
            "Goodyear",
            "Bridgestone",
            "Michelin"
        ),
        "In the Pokmon series, which type is Sudowoodo, the Imitation Pokmon?" to mutableListOf(
            "Rock",
            "Grass",
            "Bug",
            "Ground"
        ),
        "Danganronpa Another Episode: Ultra Despair Girls is set after which Danganronpa game?" to mutableListOf(
            "Danganronpa: Trigger Happy Havoc",
            "Danganronpa 2: Goodbye Despair",
            "Danganronpa V3: Killing Harmony",
            "Danganronpa 3: The End of Hopes Peak High School"
        ),
        "Which game is NOT part of the Science Adventure series by 5pb. and Nitroplus?" to mutableListOf(
            "Occultic; Nine",
            "Steins; Gate",
            "Robotics; Notes",
            "Chaos; Child"
        ),
        "What episode of Mr. Bean saw him trying to prevent people from seeing him naked?" to mutableListOf(
            "Mr. Bean in Room 426",
            "Mr. Bean Goes to Town",
            "The Trouble with Mr. Bean",
            "Back to School Mr. Bean"
        ),
        "Before the American colonies switched to the Gregorian calendar in 1752, on what date did their new year start?" to mutableListOf(
            "March 25th",
            "June 1st",
            "September 25th",
            "December 1st"
        ),
        "What is the hardest possible difficulty in Deus Ex: Mankind Divided?" to mutableListOf(
            "I Never Asked For This",
            "Nightmare",
            "Extreme",
            "Guru"
        ),
        "The musical Dirty Rotten Scoundrels is set in what country?" to mutableListOf(
            "France",
            "USA",
            "Germany",
            "Sweden"
        ),
        "Which English king was married to Eleanor of Aquitaine?" to mutableListOf(
            "Henry II",
            "Richard I",
            "Henry I",
            "Henry V"
        ),
        "What is the collective noun for bears?" to mutableListOf(
            "Sloth",
            "Drove",
            "Tribe",
            "Husk"
        ),
        "Which of the following weapons in Counter-Strike: Global Offensive does not have a right-click function?" to mutableListOf(
            "XM1014",
            "SG553",
            "R8 Revolver",
            "USP-S"
        ),
        "Which of these is not a world in the anime Buddyfight?" to mutableListOf(
            "Ancient Dragon World",
            "Dragon World",
            "Star Dragon World",
            "Darkness Dragon World"
        ),
        "Which occupation did John Tanner, the main protagonist for Driver and Driver 2, had before turning into an undercover cop?" to mutableListOf(
            "Racing Driver",
            "Taxi Driver",
            "Delivery Driver",
            "Getaway Driver"
        ),
        "Who is the main character in One Piece?" to mutableListOf(
            "Luffy",
            "Shanks",
            "Zoro",
            "Smoker"
        ),
        "Where was Nicki Minaj born?" to mutableListOf(
            "Trinidad and Tobago",
            "Haiti",
            "Saint Lucia",
            "Grenada"
        ),
        "When was Marvel Comics founded?" to mutableListOf("1939", "1932", "1951", "1936"),
        "In Splatoon, what is the age that inklings can freely change between squid and humanoid forms?" to mutableListOf(
            "14",
            "13",
            "16",
            "10"
        ),
        "The 1996 film Fargo is primarily set in which US state?" to mutableListOf(
            "Minnesota",
            "North Dakota",
            "South Dakota",
            "Wisconsin"
        ),
        "Pianist Frdric Chopin was a composer of which musical era?" to mutableListOf(
            "Romantic",
            "Classic",
            "Baroque",
            "Renaissance"
        ),
        "In the book The Martian, how long was Mark Watney trapped on Mars (in Sols)?" to mutableListOf(
            "549 Days",
            "765 Days",
            "401 Days",
            "324 Days"
        ),
        "Which type of rock is created by intense heat AND pressure?" to mutableListOf(
            "Metamorphic",
            "Sedimentary",
            "Igneous",
            "Diamond"
        ),
        "Who painted the Sistine Chapel?" to mutableListOf(
            "Michelangelo",
            "Leonardo da Vinci",
            "Pablo Picasso",
            "Raphael"
        ),
        "In Dragon Ball Z, who was the first character to go Super Saiyan 2?" to mutableListOf(
            "Gohan",
            "Goku",
            "Vegeta",
            "Trunks"
        ),
        "The most graphically violent game to precede the creation of the ESRB (Entertainment Software Rating Board) was..." to mutableListOf(
            "Mortal Kombat",
            "Duke Nukem",
            "Resident Evil",
            "Doom"
        ),
        "Which president erased the national debt of the United States?" to mutableListOf(
            "Andrew Jackson",
            "Ronald Reagan",
            "John F. Kennedy",
            "Franklin Roosevelt"
        ),
        "Who directed the movies Pulp Fiction, Reservoir Dogs and Django Unchained?" to mutableListOf(
            "Quentin Tarantino",
            "Martin Scorcese",
            "Steven Spielberg",
            "James Cameron"
        ),
        "What is Ron Weasleys middle name?" to mutableListOf("Bilius", "Arthur", "John", "Dominic"),
        "What was the worlds first video game?" to mutableListOf(
            "Tennis for Two",
            "Spacewar!",
            "Pong",
            "Space Travel"
        ),
        "What is the worlds first video game console?" to mutableListOf(
            "Magnavox Odyssey",
            "Coleco Telstar",
            "Nintendo Color TV Game",
            "Atari 2600"
        ),
        "Which of the following languages is used as a scripting language in the Unity 3D game engine?" to mutableListOf(
            "C#",
            "Java",
            "C++",
            "Objective-C"
        ),
        "Which of these is NOT a province in China?" to mutableListOf(
            "Yangtze",
            "Fujian",
            "Sichuan",
            "Guangdong"
        ),
        "Which country is singer Kyary Pamyu Pamyu from?" to mutableListOf(
            "Japan",
            "South Korea",
            "China",
            "Vietnam"
        ),
        "What country is the Hussarya supercar, made by the car manufacturer Arrinera, assembled in?" to mutableListOf(
            "Poland",
            "China",
            "Sweden",
            "Italy"
        ),
        "Which of the following European languages is classified as a language isolate?" to mutableListOf(
            "Basque",
            "Galician",
            "Maltese",
            "Hungarian"
        ),
        "What scientific family does the Aardwolf belong to?" to mutableListOf(
            "Hyaenidae",
            "Canidae",
            "Felidae",
            "Eupleridae"
        ),
        "What is the name given to Indian food cooked over charcoal in a clay oven?" to mutableListOf(
            "Tandoori",
            "Biryani",
            "Pani puri",
            "Tiki masala"
        ),
        "How many cores does the Intel i7-6950X have?" to mutableListOf("10", "12", "8", "4"),
        "Which one of these films are shot entirely in one-take?" to mutableListOf(
            "Russian Ark",
            "Good Will Hunting",
            "Birdman",
            "Schindlers List"
        ),
        "The now extinct species Thylacine was native to where?" to mutableListOf(
            "Tasmania, Australia",
            "Baluchistan, Pakistan",
            "Wallachia, Romania",
            "Oregon, United States"
        ),
        "Which of these is NOT a possible drink to be made in the game VA-11 HALL-A: Cyberpunk Bartender Action?" to mutableListOf(
            "Sour Appletini",
            "Fringe Weaver",
            "Piano Man",
            "Bad Touch"
        ),
        "In Future Diary, what is the name of Yuno Gasais Phone Diary?" to mutableListOf(
            "Yukiteru Diary",
            "Murder Diary",
            "Escape Diary ",
            "Justice Diary "
        ),
        "What do the letters in the GMT time zone stand for?" to mutableListOf(
            "Greenwich Mean Time",
            "Global Meridian Time",
            "General Median Time",
            "Glasgow Man Time"
        ),
        "In Greek Mythology, who killed Achilles?" to mutableListOf(
            "Paris",
            "Hector",
            "Helen",
            "Pericles"
        ),
        "Which company did the animation for Peter Gabriels Video Sledgehammer (1986)?" to mutableListOf(
            "Aardman Animations",
            "HIT Entertainment",
            "Illumination Entertainment",
            "VIZ Media"
        ),
        "This mobile OS held the largest market share in 2012." to mutableListOf(
            "iOS",
            "Android",
            "BlackBerry",
            "Symbian"
        ),
        "In the game Hearthstone, what is the best rank possible?" to mutableListOf(
            "Rank 1 Legend",
            "Rank 1 Elite",
            "Rank 1 Master",
            "Rank 1 Supreme"
        ),
        "Which rock band released the album The Bends in March 1995?" to mutableListOf(
            "Radiohead",
            "Nirvana",
            "Coldplay",
            "U2"
        ),
        "Virtual reality company Oculus VR lost which of its co-founders in a freak car accident in 2013?" to mutableListOf(
            "Andrew Scott Reisse",
            "Nate Mitchell",
            "Jack McCauley",
            "Palmer Luckey"
        ),
        "What was the name of Pink Floyds first studio album?" to mutableListOf(
            "The Piper at the Gates of Dawn",
            "Ummagumma",
            "More",
            "Atom Heart Mother"
        ),
        "In the To Love-Ru series, how many Trans-weapons were created?" to mutableListOf(
            "3",
            "1",
            "2",
            "4"
        ),
        "What is the name of the main antagonists in Battlestar Galactica?" to mutableListOf(
            "The Cylons",
            "The Collective",
            "The Federation",
            "The Rebels"
        ),
        "Colchester Overpass, otherwise known as Bunny Man Bridge, is located where?" to mutableListOf(
            "Fairfax County, Virginia",
            "Medford, Oregon",
            "Braxton County, Virgina",
            "Lemon Grove, California"
        ),
        "Where would you find the Spanish Steps?" to mutableListOf(
            "Rome, Italy",
            "Barcelona, Spain",
            "Berlin, Germany",
            "London, England"
        ),
        "Which of the following computer components can be built using only NAND gates?" to mutableListOf(
            "ALU",
            "CPU",
            "RAM",
            "Register"
        ),
        "Which of these Elements is a metalloid?" to mutableListOf(
            "Antimony",
            "Tin",
            "Bromine",
            "Rubidium"
        ),
        "What is the mod Cry of Fear based off of?" to mutableListOf(
            "Half-Life",
            "Counter Strike: Source",
            "Half-Life 2",
            "Its a stand alone game, not a mod"
        ),
        "Which Grand Theft Auto (GTA) games have the same setting?" to mutableListOf(
            "GTA V and GTA San Andreas",
            "GTA V and GTA Vice City",
            "GTA IV and GTA San Andreas",
            "GTA IV and GTA Vice City"
        ),
        "Which retro video game was released first?" to mutableListOf(
            "Space Invaders",
            "Galaga",
            "Pac-Man",
            "Asteroids"
        ),
        "What was Raekwon the Chefs debut solo album?" to mutableListOf(
            "Only Built 4 Cuban Linx",
            "Shaolin vs Wu-Tang",
            "The Wild",
            "The Lex Diamond Story"
        ),
        "What is not a wind instrument?" to mutableListOf("Viola", "Oboe", "Trombone", "Duduk"),
        "How many sovereign states are members of the United Nations?" to mutableListOf(
            "195",
            "201",
            "153",
            "178"
        ),
        "What is the real name of moot, founder of the imageboard 4chan?" to mutableListOf(
            "Christopher Poole",
            "Mark Zuckerberg",
            "Allison Harvard",
            "Catie Wayne"
        ),
        "Who is the Pink Floyd song Shine On You Crazy Diamond written about?" to mutableListOf(
            "Syd Barrett",
            "John Lennon",
            "David Gilmour",
            "Floyd"
        ),
        "What is Doug Walkers YouTube name?" to mutableListOf(
            "The Nostalgia Critic",
            "The Angry Video Game Nerd",
            "AngryJoeShow",
            "The Cinema Snob"
        ),
        "What is the capital of the US state Nevada?" to mutableListOf(
            "Carson City",
            "Las Vegas",
            "Henderson",
            "Reno"
        ),
        "Which artist released the 2012 single Harlem Shake, which was used in numerous YouTube videos in 2013?" to mutableListOf(
            "Baauer",
            "RL Grime",
            "NGHTMRE",
            "Flosstradamus"
        ),
        "How many bones are in the human body?" to mutableListOf("206", "203", "209", "200"),
        "What are rhinos horn made of?" to mutableListOf("Keratin", "Bone", "Ivory", "Skin"),
        "In which year did the Invasion of Kuwait by Iraq occur?" to mutableListOf(
            "1990",
            "1992",
            "1988",
            "1986"
        ),
        "How many countries does Spain have a land border with?" to mutableListOf(
            "5",
            "2",
            "3",
            "4"
        ),
        "In the game Cave Story, what is the character Balrogs catchphrase?" to mutableListOf(
            "Huzzah!",
            "Yes!",
            "Whoa there!",
            "Nyeh heh heh!"
        ),
        "What term is best associated with Sigmund Freud?" to mutableListOf(
            "Psychoanalysis",
            "Cognitive-Behavioral Therapy",
            "Theory of Gravity",
            "Dialectical Behavior Therapy"
        ),
        "In Undertale, whats the prize for answering correctly?" to mutableListOf(
            "More questions",
            "New car",
            "Mercy",
            "Money"
        ),
        "Which of these does Charlie NOT read in The Perks of Being a Wallflower?" to mutableListOf(
            "The Grapes of Wrath",
            "Hamlet",
            "The Great Gatsby",
            "Peter Pan"
        ),
        "In the movie Scream who is Ghost Face?" to mutableListOf(
            "Billy Loomis and Stu Macher",
            "Dewey Riley",
            "Sidney Prescott",
            "Archie Prescott and Philip Marv"
        ),
        "What genetic disease is caused by having an extra Y chromosome (XYY)?" to mutableListOf(
            "Jacobs Syndrome",
            "Klinefelters Syndrome",
            "Turners Syndrome",
            "Down Syndrome"
        ),
        "In 2006, which band released their debut album A Fever You Cant Sweat Out?" to mutableListOf(
            "Panic! At the Disco",
            "Twenty One Pilots",
            "My Chemical Romance",
            "Fall Out Boy"
        ),
        "When does Finland celebrate their independence day?" to mutableListOf(
            "December 6th",
            "January 2nd",
            "November 12th",
            "February 8th"
        ),
        "At the end of the 2001 film Rat Race, whose concert do the contestants crash?" to mutableListOf(
            "Smash Mouth",
            "Bowling for Soup",
            "Sum 41",
            "Linkin Park"
        ),
        "How long did World War II last?" to mutableListOf(
            "6 years",
            "4 years",
            "5 years",
            "7 years"
        ),
        "In the novel Lord of the Rings, how many rings of power were given to the race of man?" to mutableListOf(
            "9",
            "5",
            "11",
            "13"
        ),
        "Which team has won the most Stanley Cups in the NHL?" to mutableListOf(
            "Montreal Canadians",
            "Chicago Blackhawks",
            "Toronto Maple Leafs",
            "Detroit Red Wings"
        ),
        "Who is the director of the 1991 film Silence of the Lambs?" to mutableListOf(
            "Jonathan Demme",
            "Stanley Kubrick",
            "Frank Darabont",
            "Michael Bay"
        ),
        "Who voices the character Vernon Cherry in Red Dead Redemption?" to mutableListOf(
            "Casey Mongillo",
            "Tara Strong",
            "Troy Baker",
            "Rob Wiethoff"
        ),
        "Which of these characters won the Super Smash Bros. Fighter Ballot in 2015?" to mutableListOf(
            "Bayonetta",
            "Cloud",
            "Ryu",
            "Megaman"
        ),
        "Which of the following is the term for surgical complications resulting from surgical sponges left inside the patients body?" to mutableListOf(
            "Gossypiboma",
            "Gongoozler",
            "Jentacular",
            "Meupareunia"
        ),
        "Which car is NOT featured in Need for Speed: Hot Pursuit 2?" to mutableListOf(
            "Toyota MR2",
            "Ford Crown Victoria",
            "BMW Z8",
            "McLaren F1"
        ),
        "Which of the following is the IATA code for Manchester Airport?" to mutableListOf(
            "MAN",
            "EGLL",
            "LHR",
            "EGCC"
        ),
        "Which of these is a colony of polyps and not a jellyfish?" to mutableListOf(
            "Portuguese Man-of-War",
            "Sea Wasp",
            "Irukandji",
            "Sea Nettle"
        ),
        "From 1940 to 1942, what was the capital-in-exile of Free France ?" to mutableListOf(
            "Brazzaville",
            "Algiers",
            "Paris",
            "Tunis"
        ),
        "What is the Mitsubishi Wakamaru?" to mutableListOf(
            "A robot",
            "A pickup truck",
            "An motorcycle",
            "A motorboat"
        ),
        "Frankenmuth, a US city nicknamed Little Bavaria, is located in what state?" to mutableListOf(
            "Michigan",
            "Pennsylvania",
            "Kentucky",
            "Virginia"
        ),
        "Who is Manchester Uniteds leading appearance maker?" to mutableListOf(
            "Ryan Giggs",
            "David Beckham",
            "Wayne Rooney",
            "Eric Cantona"
        ),
        "Which professional wrestler fell from the rafters to his death during a live Pay-Per-View event in 1999?" to mutableListOf(
            "Owen Hart",
            "Chris Benoit",
            "Lex Luger",
            "Al Snow"
        ),
        "Which of the following mathematicians made major contributions to game theory?" to mutableListOf(
            "John Von Neumann",
            "Carl Friedrich Gauss",
            "Leonhard Euler",
            "Stefan Banach"
        ),
        "Which country has the union jack in its flag?" to mutableListOf(
            "New Zealand",
            "South Africa",
            "Canada",
            "Hong Kong"
        ),
        "Which one of these superhero teams appears in the Invincible comics?" to mutableListOf(
            "Guardians of the Globe",
            "Avengers",
            "Justice League",
            "Teenage Mutant Ninja Turtles"
        ),
        "What is the largest freshwater lake by volume?" to mutableListOf(
            "Lake Baikal",
            "Lake Superior",
            "Lake Huron",
            "Lake Michigan"
        ),
        "Where are the cars of the brand Ferrari manufactured?" to mutableListOf(
            "Italy",
            "Romania",
            "Germany",
            "Russia"
        ),
        "In which city, is the Big Nickel located in Canada?" to mutableListOf(
            "Sudbury, Ontario",
            "Calgary, Alberta",
            "Halifax, Nova Scotia ",
            "Victoria, British Columbia"
        ),
        "Which actor played the main character in the 1990 film Edward Scissorhands?" to mutableListOf(
            "Johnny Depp",
            " Clint Eastwood",
            "Leonardo DiCaprio",
            "Ben Stiller"
        ),
        "What is the largest organ of the human body?" to mutableListOf(
            "Skin",
            "Heart",
            "large Intestine",
            "Liver"
        ),
        "The original Roman alphabet lacked the following letters EXCEPT:" to mutableListOf(
            "X",
            "W",
            "U",
            "J"
        ),
        "The rights to the Rayman series are owned by which company?" to mutableListOf(
            "Ubisoft",
            "Nintendo",
            "EA",
            "Sony"
        ),
        "While Apple was formed in California, in which western state was Microsoft founded?" to mutableListOf(
            "New Mexico",
            "Washington",
            "Colorado",
            "Arizona"
        ),
        "What year was the Mona Lisa finished?" to mutableListOf("1504", "1487", "1523", "1511"),
        "How many countries border Kyrgyzstan?" to mutableListOf("4", "3", "1", "6"),
        "Which of the following countries does JoJos Bizarre Adventure: Stardust Crusaders not take place in?" to mutableListOf(
            "Philippines",
            "India",
            "Pakistan",
            "Egypt"
        ),
        "Which of these songs by artist Eminem contain the lyric Nice to meet you. Hi, my name is... I forgot my name!?" to mutableListOf(
            "Rain Man",
            "Without Me",
            "Kim",
            "Square Dance"
        ),
        "What was the cause of Marilyn Monroes suicide?" to mutableListOf(
            "Drug Overdose",
            "Knife Attack",
            "House Fire",
            "Gunshot"
        ),
        "In HTML, which non-standard tag used to be be used to make elements scroll across the viewport?" to mutableListOf(
            "marquee/marquee",
            "scroll/scroll",
            "move/move",
            "slide/slide"
        ),
        "What do you declare in Rīchi Mahjong when youve drawn your winning tile?" to mutableListOf(
            "Tsumo",
            "Ron",
            "Rīchi",
            "Kan"
        ),
        "In Baseball, how many times does the ball have to be pitched outside of the strike zone before the batter is walked?" to mutableListOf(
            "4",
            "1",
            "2",
            "3"
        ),
        "How was Socrates executed?" to mutableListOf(
            "Poison",
            "Decapitation",
            "Firing squad",
            "Crucifixion "
        ),
        "Who was featured in the song Words by Feint? " to mutableListOf(
            "Laura Brehm",
            "Anna Yvette ",
            "Danyka Nadeau",
            "Veela"
        ),
        "Of the following space shooter games, which came out first?" to mutableListOf(
            "Space Invaders",
            "Galaxian",
            "Galaga",
            "Sinistar"
        ),
        "What was the name of the protagonist in the movie Commando (1985)?" to mutableListOf(
            "John Matrix",
            "Ben Richards",
            "Douglas Quaid",
            "Harry Tasker"
        ),
        "In Star Trek: The Next Generation, what is the name of Datas cat?" to mutableListOf(
            "Spot",
            "Mittens",
            "Tom",
            "Kitty"
        ),
        "What is the first Studio Album to be released on the Internet with a Pay-What-You-Want price?" to mutableListOf(
            "In Rainbows",
            "The Help Album",
            "Skrillex and Diplo Present Jack ",
            "Blackstar"
        ),
        "What was the soft drink Pepsi originally introduced as?" to mutableListOf(
            "Brads Drink",
            "Pepsin Pop",
            "Carolina Cola",
            "Pepsin Syrup"
        ),
        "What is the unit of electrical capacitance?" to mutableListOf(
            "Farad",
            "Gauss",
            "Henry",
            "Watt"
        ),
        "What is former United States President Bill Clintons full name?" to mutableListOf(
            "William Jefferson Clinton",
            "William Roosevelt Clinton",
            "William Truman Clinton",
            "William Lincoln Clinton"
        ),
        "In Call Of Duty: Zombies, what is the name of Samantha Maxis dog?" to mutableListOf(
            "Fluffy",
            "Baxter",
            "Fido",
            "Henry"
        ),
        "When was the Siege of Leningrad lifted during World War II?" to mutableListOf(
            "January 1944",
            "September 1943",
            "November 1943",
            "March 1944"
        ),
        "Who played the Waitress in the Spam sketch of Monty Pythons Flying Circus?" to mutableListOf(
            "Terry Jones",
            "Eric Idle",
            "Graham Chapman",
            "John Cleese"
        ),
        "Which of these was an official candidate in the 2017 British General Election?" to mutableListOf(
            "Lord Buckethead",
            "James Francis",
            "Robert Wimbledon",
            "Sir Crumpetsby"
        ),
        "Which group performs the song Crash into Me?" to mutableListOf(
            "Dave Matthews Band",
            "Phish",
            "The Grateful Dead",
            "Destinys Child"
        ),
        "What is the weight of a Gold Bar in Fallout: New Vegas?" to mutableListOf(
            "35 Pounds",
            "30 Pounds",
            "40 Pounds",
            "32.50 Pounds"
        ),
        "Who won the UEFA Champions League in 2016?" to mutableListOf(
            "Real Madrid C.F.",
            "FC Bayern Munich",
            "Atletico Madrid",
            "Manchester City F.C."
        ),
        "What type of sound chip does the Super Nintendo Entertainment System (SNES) have?" to mutableListOf(
            "ADPCM Sampler",
            "FM Synthesizer",
            "Programmable Sound Generator (PSG)",
            "PCM Sampler"
        ),
        "Dry ice is the solid form of what substance?" to mutableListOf(
            "Carbon dioxide",
            "Nitrogen",
            "Ammonia",
            "Oxygen"
        ),
        "Which of these are NOT a Men at Work song?" to mutableListOf(
            "Basket Case",
            "Dr. Heckyll and Mr. Jive",
            "Who Can It Be Now?",
            "Be Good Johnny"
        ),
        "Who is the main protagonist in the game Life is Strange: Before The Storm?" to mutableListOf(
            "Chloe Price ",
            "Max Caulfield",
            "Rachel Amber",
            "Frank Bowers"
        ),
        "Which year was the album Year of the Snitch by Death Grips released?" to mutableListOf(
            "2018",
            "2013",
            "2017",
            "2011"
        ),
        "In the video game Postal 2, what is the name of Postal Dudes dog?" to mutableListOf(
            "Champ",
            "Snoopy",
            "Krotchy",
            "Duke"
        ),
        "Who played Deputy Marshal Samuel Gerard in the 1993 film The Fugitive?" to mutableListOf(
            "Tommy Lee Jones",
            "Harrison Ford",
            "Harvey Keitel",
            "Martin Landau"
        ),
        "How many manned moon landings have there been?" to mutableListOf("6", "1", "3", "7"),
        "What does AD stand for in relation to Windows Operating Systems? " to mutableListOf(
            "Active Directory",
            "Alternative Drive",
            "Automated Database",
            "Active Department"
        ),
        "What is the powerhouse of the cell?" to mutableListOf(
            "Mitochondria",
            "Ribosome",
            "Redbull",
            "Nucleus"
        ),
        "In the programming language Java, which of these keywords would you put on a variable to make sure it doesnt get modified?" to mutableListOf(
            "Final",
            "Static",
            "Private",
            "Public"
        ),
        "What is the chemical makeup of water?" to mutableListOf("H20", "C12H6O2", "CO2", "H"),
        "What name was historically used for the Turkish city currently known as Istanbul?" to mutableListOf(
            "Constantinople",
            "Hdavendigar",
            "Sğt",
            "Adrianople"
        ),
        "Who is the lead singer of the British pop rock band Coldplay?" to mutableListOf(
            "Chris Martin",
            "Jonny Buckland",
            "Guy Berryman",
            "Will Champion"
        ),
        "Bohdan Khmelnytsky was which of the following?" to mutableListOf(
            "Leader of the Ukrainian Cossacks",
            "General Secretary of the Communist Party of the USSR",
            "Prince of Wallachia",
            "Grand Prince of Novgorod"
        ),
        "Which country does the power metal band Sabaton originate from?" to mutableListOf(
            "Sweden",
            "Germany",
            "United States",
            "Finland"
        ),
        "Which company did Bethesda purchase the Fallout Series from?" to mutableListOf(
            "Interplay Entertainment ",
            "Capcom",
            "Blizzard Entertainment",
            "Nintendo"
        ),
        "Gwyneth Paltrow has a daughter named...?" to mutableListOf(
            "Apple",
            "Lily",
            "French",
            "Dakota"
        ),
        "In Macbeth, the eyes of what animals were used in the Witches cauldron?" to mutableListOf(
            "Newts",
            "Humans",
            "Sharks",
            "Squids"
        ),
        "What is Canadas smallest province?" to mutableListOf(
            "Prince Edward Island",
            "New Brunswick",
            "Nova Scotia",
            "Yukon"
        ),
        "Who is the lead singer of Bastille?" to mutableListOf(
            "Dan Smith",
            "Will Farquarson",
            "Kyle Simmons",
            "Chris Wood"
        ),
        "What is the name of New Zealands indigenous people?" to mutableListOf(
            "Maori",
            "Vikings",
            "Polynesians",
            "Samoans"
        ),
        "In Digimon, what is the Japanese name for the final evolutionary stage?" to mutableListOf(
            "Ultimate",
            "Mega",
            "Adult",
            "Champion"
        ),
        "What type of cheese, loved by Wallace and Gromit, had its sale prices rise after their successful short films?" to mutableListOf(
            "Wensleydale",
            "Cheddar",
            "Moon Cheese",
            "Edam"
        ),
        "Medaka Kurokami from Medaka Box has what abnormality?" to mutableListOf(
            "The End",
            "Perfection",
            "Sandbox",
            "Fairness"
        ),
        "Which of these games was NOT a Nintendo Switch launch title in the United States? " to mutableListOf(
            "Voez",
            "Just Dance 2017",
            "Snipperclips",
            "Fast RMX"
        ),
        "In the game Brawlhalla, what species is the character Bdvar is?" to mutableListOf(
            "Half Human / Half Bear",
            "A Human",
            "Half Wolf / Half Bear",
            "Half Tiger /  Half Human"
        ),
        "When was the video game P.A.M.E.L.A. released on Steam?" to mutableListOf(
            "March 9, 2017",
            "January 7, 2007",
            "October 23, 1997",
            "February 16, 2015"
        ),
        "What is the full name of the footballer Cristiano Ronaldo?" to mutableListOf(
            "Cristiano Ronaldo dos Santos Aveiro",
            "Cristiano Ronaldo los Santos Diego",
            "Cristiano Armando Diego Ronaldo",
            "Cristiano Luis Armando Ronaldo"
        ),
        "What was the name of the hip hop group Kanye West was a member of in the late 90s?" to mutableListOf(
            "The Go-Getters",
            "The Jumpers",
            "The Kickstarters",
            "The Beat-Busters"
        ),
        "Which of the following is not one of the Greek Fates?" to mutableListOf(
            "Narcissus",
            "Clotho",
            "Atropos",
            "Lachesis"
        ),
        "In Call of Duty: Zombies, what group does Doctor Maxis work for?" to mutableListOf(
            "Group 935",
            "Group Reanimate",
            "Group Rezurrection",
            "Division 9"
        ),
        "In the 1980s, a service called Gameline allowed users to download games to what console?" to mutableListOf(
            "Atari 2600",
            "Sega Genesis",
            "Nintendo Entertainment System",
            "ColecoVision"
        ),
        "Who was the inspiration for Cuthbert Calculus in the Tintin series?" to mutableListOf(
            "Auguste Picard",
            "Jacques Piccard",
            "Will Morris",
            "J. Cecil Maby"
        ),
        "Which of the following games was NOT included in Valves Orange Box?" to mutableListOf(
            "Counter-Strike",
            "Portal",
            "Half-Life 2: Episode Two",
            "Team Fortress 2"
        ),
        "In the Friday The 13th series, what is Jasons mothers first name?" to mutableListOf(
            "Pamela",
            "Mary",
            "Christine",
            "Angeline"
        ),
        "Who was Kung Fu Fighting in 1974?" to mutableListOf(
            "Carl Douglas",
            "The Bee Gees",
            "Heatwave",
            "Kool  the Gang"
        ),
        "How many protons are in an oxygen atom?" to mutableListOf("Eight", "Four", "Two", "Six"),
        "Which Final Fantasy game consisted of a female-only cast of party members?" to mutableListOf(
            "Final Fantasy X-2",
            "Final Fantasy IX",
            "Final Fantasy V",
            "Final Fantasy XIII-2"
        ),
        "What is the full title of the Prime Minister of the UK?" to mutableListOf(
            "First Lord of the Treasury",
            "Duke of Cambridge",
            "Her Majestys Loyal Opposition",
            "Manager of the Crown Estate"
        ),
        "In the Team Fortress 2 canon, what did Shakespearicles NOT invent?" to mutableListOf(
            "Stairs",
            "Two-Story Building",
            "Rocket Launcher",
            "Stage Play"
        ),
        "What is the name of Team Fortress 2 update, in which it became Free-to-play?" to mutableListOf(
            "ber Update",
            "Pyromania Update",
            "Mann-Conomy Update",
            "Engineer Update"
        ),
        "In Marvel Comics, Taurus is the founder and leader of which criminal organization?" to mutableListOf(
            "Zodiac",
            "Scorpio",
            "Tiger Mafia",
            "The Union"
        ),
        "How many books are in the Chronicles of Narnia series?" to mutableListOf(
            "7",
            "6",
            "8",
            "5"
        ),
        "In the anime Mr. Osomatsu, how many brothers does Osomatsu-san have?" to mutableListOf(
            "5",
            "6",
            "7",
            "4"
        ),
        "Who was the Prime Minister of the United Kingdom for most of World War II?" to mutableListOf(
            "Winston Churchill",
            "Neville Chamberlain",
            "Harold Macmillan",
            "Edward Heath"
        ),
        "Which of the following is NOT an official game in Nintendos Super Smash Bros. series?" to mutableListOf(
            "Super Smash Bros. Crusade",
            "Super Smash Bros. Melee",
            "Super Smash Bros. Brawl",
            "Super Smash Bros. for Nintendo 3DS and Wii U"
        ),
        "Which football player is featured on the international cover version of the video game FIFA 16?" to mutableListOf(
            "Lionel Messi",
            "Cristiano Ronaldo",
            "Wayne Rooney",
            "David Beckham"
        ),
        "Whats the Team Fortress 2 Scouts city of origin?" to mutableListOf(
            "Boston",
            "Sydney",
            "Detroit",
            "New York"
        ),
        "What was Rage Against the Machines debut album?" to mutableListOf(
            "Rage Against the Machine",
            "Evil Empire",
            "Bombtrack",
            "The Battle Of Los Angeles"
        ),
        "In the Homestuck Series, what is the alternate name for the Kingdom of Lights?" to mutableListOf(
            "Prospit",
            "No Name",
            "Golden City",
            "Yellow Moon"
        ),
        "According to Toby Fox, what was the method to creating the initial tune for Megalovania?" to mutableListOf(
            "Singing into a Microphone",
            "Playing a Piano",
            "Using a Composer Software",
            "Listened to birds at the park"
        ),
        "How many flagship monsters appear in Monster Hunter Gernerations?" to mutableListOf(
            "4",
            "1",
            "2",
            "3"
        ),
        "Which Nirvana album had a naked baby on the cover?" to mutableListOf(
            "Nevermind",
            "Bleach",
            "In Utero",
            "Incesticide"
        ),
        "How many members are in the Japanese rock band SCANDAL?" to mutableListOf(
            "4",
            "5",
            "2",
            "18"
        ),
        "What are the cylinder-like parts that pump up and down within the engine?" to mutableListOf(
            "Pistons",
            "Leaf Springs",
            "Radiators",
            "ABS"
        ),
        "What is the highest mountain in the world?" to mutableListOf(
            "Mt. Everest",
            "Mount Godwin Austen",
            "Kangchenjunga",
            "Annapurna"
        ),
        "When Donkey Kong died in the Donkey Kong Country episode Its a Wonderful Life, who was his guardian angel?" to mutableListOf(
            "Eddie the Mean Old Yeti",
            "Kiddy Kong",
            "Diddy Kong",
            "King K. Rool"
        ),
        "The LS7 engine is how many cubic inches?" to mutableListOf("427", "346", "364", "376"),
        "What does the term isolation refer to in microbiology?" to mutableListOf(
            "The separation of a strain from a natural, mixed population of living microbes",
            "A lack of nutrition in microenviroments",
            "The nitrogen level in soil",
            "Testing effects of certain microorganisms in an isolated enviroments, such as caves"
        ),
        "When did the website Facebook launch?" to mutableListOf("2004", "2005", "2003", "2006"),
        "What programming language was used to create the game Minecraft?" to mutableListOf(
            "Java",
            "HTML 5",
            "C++",
            "Python"
        ),
        "In the Re:Zero manga series, which of the following Sin Archbishops eats Rems existence?" to mutableListOf(
            "Ley Batenkaitos",
            "Roy Alphard",
            "Petelgeuse Romanee-Conti",
            "Louis Arneb"
        ),
        "What is the only Generation III Pokemon whose name begins with the letter I?" to mutableListOf(
            "Illumise",
            "Infernape",
            "Ivysaur",
            "Igglybuff"
        ),
        "Which town is the setting for the Disney movie The Love Bug (1968)?" to mutableListOf(
            "San Francisco",
            "Los Angeles",
            "Sacramento",
            "San Jose"
        ),
        "Nephelococcygia is the practice of doing what?" to mutableListOf(
            "Finding shapes in clouds",
            "Sleeping with your eyes open",
            "Breaking glass with your voice",
            "Swimming in freezing water"
        ),
        "In the S.T.A.L.K.E.R. series, which of these items cant be used to lower the players accumulated radiation?" to mutableListOf(
            "Radioprotectant",
            "Anti-rad",
            "Medkit",
            "Vodka"
        ),
        "In Kingdom Hearts which of the following people can NOT wield a keyblade?" to mutableListOf(
            "Larxene",
            "Xehanort",
            "Lea",
            "Mickey"
        ),
        "Which novel by John Grisham was conceived on a road trip to Florida while thinking about stolen books with his wife?" to mutableListOf(
            "Camino Island",
            "Rogue Lawyer",
            "Gray Mountain",
            "The Litigators"
        ),
        "The emblem on the flag of the Republic of Tajikistan features a sunrise over mountains below what symbol?" to mutableListOf(
            "Crown",
            "Bird",
            "Sickle",
            "Tree"
        ),
        "The painting The Starry Night by Vincent van Gogh was part of which art movement?" to mutableListOf(
            "Post-Impressionism",
            "Romanticism",
            "Neoclassical",
            "Impressionism"
        ),
        "Which of these is NOT a real tectonic plate?" to mutableListOf(
            "Atlantic Plate",
            "North American Plate",
            "Eurasian Plate",
            "Nazca Plate"
        ),
        "How many books are in Euclids Elements of Geometry?" to mutableListOf(
            "13",
            "8",
            "10",
            "17"
        ),
        "In the film Requiem for a Dream, what drug does Jared Letos character get addicted to?" to mutableListOf(
            "Heroin",
            "Cocaine",
            "Marijuana",
            "Oxycodone"
        ),
        "In the co-op shooter Payday 2, which contact helps you break out Hoxton?" to mutableListOf(
            "The Dentist",
            "Vlad",
            "The Elephant",
            "The Butcher"
        ),
        "Who is the second person to take up the mantle of Night Owl in the Watchmen graphic novel?" to mutableListOf(
            "Daniel Dreiberg",
            "Nelson Gardner",
            "Hollis Mason",
            "Adrian Veidt"
        ),
        "How many obsidian blocks are required to build a nether portal in Minecraft?" to mutableListOf(
            "10",
            "14",
            "13",
            "16"
        ),
        "Who was the villain of The Lion King?" to mutableListOf("Scar", "Fred", "Jafar", "Vada"),
        "What year did the television company BBC officially launch the channel BBC One?" to mutableListOf(
            "1936",
            "1948",
            "1932",
            "1955"
        ),
        "When was the Declaration of Independence approved by the Second Continental Congress?" to mutableListOf(
            "July 4, 1776",
            "May 4, 1776",
            "June 4, 1776",
            "July 2, 1776"
        ),
        "What name represents the letter M in the NATO phonetic alphabet?" to mutableListOf(
            "Mike",
            "Matthew",
            "Mark",
            "Max"
        ),
        "What town was Springfield from The Simpsons originally named after?" to mutableListOf(
            "Springfield, Oregon",
            "Springfield, Missouri",
            "Springfield, Illinois",
            "Springfield, Massachusetts"
        ),
        "What was the name of the hero in the 80s animated video game Dragons Lair?" to mutableListOf(
            "Dirk the Daring",
            "Arthur",
            "Sir Toby Belch",
            "Guy of Gisbourne"
        ),
        "Which of these countries is doubly landlocked (surrounded entirely by one or more landlocked countries)?" to mutableListOf(
            "Uzbekistan",
            "Switzerland",
            "Bolivia",
            "Ethiopia"
        ),
        "What is the scientific name for modern day humans?" to mutableListOf(
            "Homo Sapiens",
            "Homo Ergaster",
            "Homo Erectus",
            "Homo Neanderthalensis"
        ),
        "This movie contains the quote, What weve got here is a failure to communicate." to mutableListOf(
            "Cool Hand Luke",
            "Bonnie and Clyde",
            "The Graduate",
            "In the Heat of the Night"
        ),
        "The Tibia is found in which part of the body?" to mutableListOf(
            "Leg",
            "Arm",
            "Hand",
            "Head"
        ),
        "What is the name of the first Star Wars film by release order?" to mutableListOf(
            "A New Hope",
            "The Phantom Menace",
            "The Force Awakens",
            "Revenge of the Sith"
        ),
        "How many known living species of hyenas are there?" to mutableListOf("4", "8", "2", "6"),
        "What does the acronym CDN stand for in terms of networking?" to mutableListOf(
            "Content Delivery Network",
            "Content Distribution Network",
            "Computational Data Network",
            "Compressed Data Network"
        ),
        "Who is the protagonist of the videogame Dead Rising 2?" to mutableListOf(
            "Chuck Greene",
            "Nick Ramos",
            "Frank West",
            "Katie Greene"
        ),
        "Which one is NOT the function of engine oil in car engines?" to mutableListOf(
            "Combustion",
            "Lubrication",
            "Cooling",
            "Reduce corrosion"
        ),
        "What is the correct phrase for the Latin saying Romanes Eunt Domus in Monty Pythons Life of Brian?" to mutableListOf(
            "Romani Ite Domum",
            "Romans Go Home",
            "Roxani Ite Domum",
            "Tomate Ite Domum"
        ),
        "On a standard Monopoly board, how much do you have to pay for Tennessee Ave?" to mutableListOf(
            "$180",
            "$200",
            "$160",
            "$220"
        ),
        "Which American civilization is the source of the belief that the world would end or drastically change on December 21st, 2012?" to mutableListOf(
            "The Mayans",
            "The Incas",
            "The Aztecs",
            "The Navajos"
        ),
        "In the Portal series of games, who was the founder of Aperture Science?" to mutableListOf(
            "Cave Johnson",
            "GLaDOs",
            "Wallace Breen",
            "Gordon Freeman"
        ),
        "What is the first track on Kanye Wests 808s  Heartbreak?" to mutableListOf(
            "Say You Will",
            "Welcome to Heartbreak",
            "Street Lights",
            "Heartless"
        ),
        "Who played the Cenobite called Pinhead in the original Hellraiser films?" to mutableListOf(
            "Doug Bradley",
            "Doug Jones",
            "Doug Savant",
            "Doug Benson"
        ),
        "Who created the Cartoon Network series Regular Show?" to mutableListOf(
            "J. G. Quintel",
            "Ben Bocquelet",
            "Pendleton Ward",
            "Rebecca Sugar"
        ),
        "Valve Corporation is an American video game developer located in which city?" to mutableListOf(
            "Bellevue, Washington",
            "Austin, Texas",
            "Seattle, Washington",
            "San Francisco, California"
        ),
        "Botanically speaking, which of these fruits is NOT a berry?" to mutableListOf(
            "Strawberry",
            "Blueberry",
            "Banana",
            "Concord Grape"
        ),
        "Which monster in Monster Hunter Tri was causing earthquakes in Moga Village?" to mutableListOf(
            "Ceadeus",
            "Alatreon",
            "Rathalos",
            "Lagiacrus"
        ),
        "Which of these is not a single by Pink Floyd guitarist, David Gilmour?" to mutableListOf(
            "Sunset Strip",
            "Rattle That Lock",
            "Blue Light",
            "Arnold Layne"
        ),
        "Who played Charlie Price in the musical Kinky Boots on Broadway in New York from May 26th - Aug 6th 2017?" to mutableListOf(
            "Brendon Urie",
            "Ed Sheeren",
            "Tom Cruise",
            "Dallon Weekes"
        ),
        "In which mall does Dead Rising take place?" to mutableListOf(
            "Willamette Parkview Mall",
            "Liberty Mall",
            "Twin Pines Mall",
            "Central Square Shopping Center"
        ),
        "The Dice Tower network of board game podcasts and videos is run by which individual?" to mutableListOf(
            "Tom Vasel",
            "Jason LeVine",
            "Borth Sampson",
            "Uncle Pennybags"
        ),
        "Whose greyscale face is on the kappa emoticon on Twitch?" to mutableListOf(
            "Josh DeSeno",
            "Justin DeSeno",
            "John DeSeno",
            "Jimmy DeSeno"
        ),
        "What studio animated Fullmetal Alchemist?" to mutableListOf(
            "Bones",
            "Trigger",
            "Pierrot",
            "xebec"
        ),
        "In the Kingdom Hearts series, which is not an optional boss you can fight?" to mutableListOf(
            "Master Yen Sid",
            "Sephiroth",
            "Julius",
            "Kurt Zisa"
        ),
        "In Rick And Morty, who shot Mr. Poopybutthole in the episode Total Rickall?" to mutableListOf(
            "Beth",
            "Rick",
            "Jerry",
            "Morty"
        ),
        "What is Stenoma?" to mutableListOf(
            "A genus of moths",
            "A combat stimulant from WW2",
            "A type of seasoning",
            "A port city in the carribean"
        ),
        "What is Tiger Woods all-time best career golf-score?" to mutableListOf(
            "61",
            "65",
            "63",
            "67"
        ),
        "The Andrew Lloyd Webber musical Cats is based off a book of poems written by which author?" to mutableListOf(
            "T.S. Elliot",
            "Andrew Lloyd Webber",
            "Emily Dickenson",
            "Robert Frost"
        ),
        "Which Family Guy character got his own spin-off show in 2009?" to mutableListOf(
            "Cleveland Brown",
            "Glenn Quagmire",
            "Joe Swanson",
            "The Greased-up Deaf Guy"
        ),
        "In the Super Mario Bros. Series, what is Yoshis scientific name?" to mutableListOf(
            "T. Yoshisaur Munchakoopas",
            "Yoshi",
            "T. Yoshisotop Munchakoopas",
            "Yossy"
        ),
        "In what year did the Wall Street Crash take place?" to mutableListOf(
            "1929",
            "1932",
            "1930",
            "1925"
        ),
        "If a 360 no-scope is one full rotation before shooting, how many rotations would a 1080 no-scope be?" to mutableListOf(
            "3",
            "4",
            "2",
            "5"
        ),
        "What year was Canada founded in?" to mutableListOf("1867", "1798", "1859", "1668"),
        "Which infamous European traitor was known as the last person to enter Parliament with honest intentions?" to mutableListOf(
            "Guy Fawkes",
            "Robert Catesby",
            "Francis Tresham",
            "Everard Digby"
        ),
        "What was the first Team Fortress 2 update to include a war?" to mutableListOf(
            "Sniper vs. Spy Update",
            "WAR! Update",
            "Meet Your Match Update",
            "Spy Vs. Engineer Update"
        ),
        "Who composed the soundtrack for the game VVVVVV?" to mutableListOf(
            "Magnus Plsson",
            "Terry Cavanagh",
            "Danny Baranowsky",
            "Joel Zimmerman"
        ),
        "Due to the Nagoya Resolution, China agreed to allow Taiwan to compete separately in international sporting events under what name?" to mutableListOf(
            "Chinese Taipei",
            "Chinese Taiwan",
            "Republic of Taiwan",
            "Republic of Taipei "
        ),
        "Which artist collaborated with American DJ Dillon Francis to release the song 2016 Need You?" to mutableListOf(
            "NGHTMRE",
            "LOUDPVCK",
            "KRNE",
            "DVBBS"
        ),
        "In what year did the North American Video Game Crash occur?" to mutableListOf(
            "1983",
            "1982",
            "1993",
            "1970"
        ),
        "What is the right way to spell the capital of Hungary?" to mutableListOf(
            "Budapest",
            "Boodapest",
            "Bhudapest",
            "Budapast"
        ),
        "In the game Terraria, which of these bosses are pre-hardmode bosses?" to mutableListOf(
            "Eye of Cthulhu",
            "Plantera",
            "Skeletron Prime",
            "The Destroyer"
        ),
        "In The Binding of Isaac, which item instantly kills Mom and Moms Heart?" to mutableListOf(
            "The Bible",
            "The Halo",
            "Brimstone",
            "Book of Shadows"
        ),
        "Which programming language was developed by Sun Microsystems in 1995?" to mutableListOf(
            "Java",
            "Python",
            "Solaris OS",
            "C++"
        ),
        "In the TV show Mad Men, what was Donald Drapers birthname?" to mutableListOf(
            "Richard Dick Whitman",
            "Donald Draper",
            "John Ashbury",
            "Michael Mikey Wilhelm"
        ),
        "Which actor plays the character Tommy Jarvis in Friday the 13th: The Final Chapter (1984)?" to mutableListOf(
            "Corey Feldman",
            "Macaulay Culkin",
            "Mel Gibson",
            "Mark Hamill"
        ),
        "In Magic: The Gathering, what instant card has the highest converted mana cost?" to mutableListOf(
            "Blinkmoth Infusion",
            "Vitalizing Wind",
            " Chant of Vitu-Ghazi",
            "Assert Authority"
        ),
        "Which gaming series includes The Diabolical Box and The Curious Village?" to mutableListOf(
            "Professor Layton",
            "Persona",
            "Etrian Odyssey",
            "Sam  Max"
        ),
        "Originally another word for poppy, coquelicot is a shade of what?" to mutableListOf(
            "Red",
            "Green",
            "Blue",
            "Pink"
        ),
        "Which country was NOT part of the Soviet Union?" to mutableListOf(
            "Romania",
            "Turkmenistan",
            "Belarus",
            "Tajikistan"
        ),
        "What is the name of the main protagonist in Xenoblade Chronicles?" to mutableListOf(
            "Shulk",
            "Reyn",
            "Fiora",
            "Dunban"
        ),
        "What is the default name of the Vampire character in Shining Soul 2." to mutableListOf(
            "Bloodstar",
            "Sachs",
            "Dracuul",
            "Alucard"
        ),
        "Chino Moreno is the lead singer of which alternative metal band?" to mutableListOf(
            "Deftones",
            "Tool",
            "Korn",
            "Type O Negative"
        ),
        "In the 1968 Cartoon Show Wacky Races what was the name of cavemen duo who rode in The Boulder Mobile?" to mutableListOf(
            "The Slag Brothers",
            "The Slate Brothers",
            "The Rock Brothers",
            "The Stone Brothers"
        ),
        "In board games, an additional or ammended rule that applies to a certain group or place is informally known as a what rule?" to mutableListOf(
            "House",
            "Custom",
            "Extra",
            "Change"
        ),
        "According to Japanese folklore, what is the favorite food of the Kappa." to mutableListOf(
            "Cucumbers",
            "Kabocha",
            "Nasu",
            "Soba"
        ),
        "What character is NOT apart of the Grand Theft Auto series?" to mutableListOf(
            "Michael Cardenas",
            "Packie McReary",
            "Tommy Vercetti",
            "Lester Crest"
        ),
        "What was Humphrey Bogarts middle name?" to mutableListOf(
            "DeForest",
            "DeWinter",
            "Steven",
            "Bryce"
        ),
        "In what year was the M1911 pistol designed?" to mutableListOf(
            "1911",
            "1907",
            "1899",
            "1917"
        ),
        "Which of these plays was famously first performed posthumously after the playwright committed suicide?" to mutableListOf(
            "4.48 Psychosis",
            "Hamilton",
            "Much Ado About Nothing",
            "The Birthday Party"
        ),
        "Which of the following elements is typically used in the doping of the semiconductor silicon?" to mutableListOf(
            "Boron",
            "Oxygen",
            "Carbon",
            "Iron"
        ),
        "How much money did the 2019 Marvel movie Avengers: Endgame grossed for its record-breaking worldwide opening weekend?" to mutableListOf(
            "1.2 billion USD",
            "640 million USD",
            "456 million USD",
            "392 million USD"
        ),
        "According to Algonquian folklore, how does one transform into a Wendigo?" to mutableListOf(
            "Participating in cannibalism.",
            "Excessive mutilation of animal corpses.",
            "Performing a ritual involving murder.",
            "Drinking the blood of many slain animals."
        ),
        "In which series of games do you collect souls to empower you and buy weaponry and armor with?" to mutableListOf(
            "Souls ",
            "Final Fantasy ",
            "Monster Hunter",
            "The Legend of Zelda"
        ),
        "What cricketing term denotes a batsman being dismissed with a score of zero?" to mutableListOf(
            "Duck",
            "Bye",
            "Beamer",
            "Carry"
        ),
        "In relation to the British Occupation in Ireland, what does the IRA stand for." to mutableListOf(
            "Irish Republican Army",
            "Irish Rebel Alliance",
            "Irish Reformation Army",
            "Irish-Royal Alliance"
        ),
        "What was the capital of South Vietnam before the Vietnam War?" to mutableListOf(
            "Saigon",
            "Ho Chi Minh City",
            "Hanoi",
            "Hue"
        ),
        "Which sign of the zodiac is represented by the Crab?" to mutableListOf(
            "Cancer",
            "Libra",
            "Virgo",
            "Sagittarius"
        ),
        "What alcoholic drink is made from molasses?" to mutableListOf(
            "Rum",
            "Gin",
            "Vodka",
            "Whisky"
        ),
        "In what year was the original Sonic the Hedgehog game released?" to mutableListOf(
            "1991",
            "1989",
            "1993",
            "1995"
        ),
        "Which song in rapper Kanye Wests album The Life of Pablo features Rihanna?" to mutableListOf(
            "Famous",
            "Wolves",
            "Ultralight Beam",
            "Highlights"
        ),
        "Which of the following is NOT classified as a Semetic language?" to mutableListOf(
            "Sumerian",
            "Maltese",
            "Akkadian",
            "Mandaic"
        ),
        "Which of the following Assyrian kings did NOT rule during the Neo-Assyrian Empire?" to mutableListOf(
            "Shamshi-Adad III",
            "Shalmaneser V",
            "Esharhaddon",
            "Ashur-nasir-pal II"
        ),
        "Which of the following former Yugoslavian states is landlocked?" to mutableListOf(
            "Serbia",
            "Bosnia and Herzegovina",
            "Montenegro",
            "Croatia"
        ),
        "What is the name of Finnish DJ Darudes hit single released in October 1999?" to mutableListOf(
            "Sandstorm",
            "Dust Devil",
            "Sirocco",
            "Khamsin"
        ),
        "Which game was exclusive to Dreamcast?" to mutableListOf(
            "Pen Pen TriIcelon",
            "Sylvester  Tweety in Cagey Capers",
            "Perfect Dark",
            "Tetrisphere"
        ),
        "What is the scientific name of the Budgerigar?" to mutableListOf(
            "Melopsittacus undulatus",
            "Nymphicus hollandicus",
            "Pyrrhura molinae",
            "Ara macao"
        ),
        "In Overwatch, what is the name of Mercys ultimate ability?" to mutableListOf(
            "Valkyrie",
            "Earthshatter",
            "Rocket Barrage",
            "Molten Core"
        ),
        "Which Beatles album does NOT feature any of the band members on its cover?" to mutableListOf(
            "The Beatles (White Album)",
            "Rubber Soul",
            "Abbey Road",
            "Magical Mystery Tour"
        ),
        "In The Melancholy of Haruhi Suzumiya series, the SOS Brigade club leader is unknowingly treated as a(n) __ by her peers." to mutableListOf(
            "God",
            "Alien",
            "Time Traveler",
            "Esper"
        ),
        "Who is the original author of the realtime physics engine called PhysX?" to mutableListOf(
            "NovodeX",
            "Ageia",
            "Nvidia",
            "AMD"
        ),
        "What was the first monster to appear alongside Godzilla?" to mutableListOf(
            "Anguirus",
            "King Kong",
            "Mothra",
            "King Ghidora"
        ),
        "What is the name of rocky region that spans most of eastern Canada?" to mutableListOf(
            "Canadian Shield",
            "Rocky Mountains",
            "Appalachian Mountains",
            "Himalayas"
        ),
        "Which day in Papers, Please does the man in red appear?" to mutableListOf(
            "Day 23",
            "Day 20",
            "Day 17",
            "Day 15"
        ),
        "Satella in Re:Zero is the witch of what?" to mutableListOf(
            "Envy",
            "Pride",
            "Sloth",
            "Wrath"
        ),
        "In Star Trek Nemesis, why was Praetor Shinzon created?" to mutableListOf(
            "To replace Picard as a Romulan Agent",
            "To destroy the Enterprise",
            "To become Picards friend ",
            "To steal the Enterprise"
        ),
        "Who voices the character Reigen  in the English dub of Mob Psycho 100?" to mutableListOf(
            "Chris Niosi",
            "Max Mittelman",
            "Kyle McCarley",
            "Casey Mongillo"
        ),
        "What was the first Call Of Duty: Zombies map to be directed by Jason Blundell?" to mutableListOf(
            "Mob Of The Dead",
            "Buried",
            "Origins",
            "Moon"
        ),
        "What is the perk that was introduced in the Call Of Duty: Zombies map, Mob Of The Dead?" to mutableListOf(
            "Electric Cherry",
            "Quick Revive",
            "Vulture Aid",
            "Tombstone"
        ),
        "In the Call Of Duty: Zombies map Origins, how many numbered power generators are there?" to mutableListOf(
            "6",
            "8",
            "5",
            "3"
        ),
        "In To Love-Ru: Darkness, which of the girls attempt making a harem for Rito Yuuki?" to mutableListOf(
            "Momo Deviluke",
            "Yami (Golden Darkness)",
            "Haruna Sairenji",
            "Mea Kurosaki"
        ),
        "What animal takes part in Schrdingers most famous thought experiment?" to mutableListOf(
            "Cat",
            "Dog",
            "Bat",
            "Butterfly"
        ),
        "Which of these countrys capitals starts with the letter B?" to mutableListOf(
            "Lebanon",
            "Jordan",
            "Kuwait",
            "Qatar"
        ),
        "Who was the winner of Big Brother Season 10?" to mutableListOf(
            "Dan Gheesling",
            "Bryce Kranyik",
            "Ryan Sutfin",
            "Chris Mundorf"
        ),
        "The In the Flesh tour was used in support of what Pink Floyd album?" to mutableListOf(
            "Animals",
            "The Wall",
            "Wish You Were Here",
            "The Final Cut"
        ),
        "What is the highest number of Michelin stars a restaurant can receive?" to mutableListOf(
            "Three",
            "Four",
            "Five",
            "Six"
        ),
        "Which year did Jenson Button won his first ever Formula One World Drivers Championship?" to mutableListOf(
            "2009",
            "2010",
            "2007",
            "2006"
        ),
        "In Touhou 12: Undefined Fantastic Object, which of these was not a playable character?" to mutableListOf(
            "Izayoi Sakuya",
            "Hakurei Reimu",
            "Kirisame Marisa",
            "Kochiya Sanae"
        ),
        "In quantum physics, which of these theorised sub-atomic particles has yet to be observed?" to mutableListOf(
            "Graviton",
            "Z boson",
            "Tau neutrino",
            "Gluon"
        ),
        "What European country is not a part of the EU?" to mutableListOf(
            "Norway",
            "Lithuania",
            "Ireland",
            "Czechia"
        ),
        "Who was the British Prime Minister at the outbreak of the Second World War?" to mutableListOf(
            "Neville Chamberlain",
            "Clement Attlee",
            "Winston Churchill",
            "Stanley Baldwin"
        ),
        "Which German field marshal was known as the `Desert Fox`?" to mutableListOf(
            "Erwin Rommel",
            "Ernst Busch",
            "Wolfram Freiherr von Richthofen",
            "Wilhelm List"
        ),
        "The Tsar Bomba, the most powerful nuclear bomb ever tested, had a yield of 50 megatons but theoretically had a maximum yield of how much?" to mutableListOf(
            "100 Megatons",
            "200 Megatons",
            "75 Megatons",
            "150 Megatons"
        ),
        "Which of these is NOT a player class in Team Fortress 2?" to mutableListOf(
            "Healer",
            "Demoman",
            "Pyro",
            "Spy"
        ),
        "Which popular rock band has a one-armed drummer?" to mutableListOf(
            "Def Leppard",
            "The Beatles",
            "Lynyrd Skynyrd",
            "Foreigner"
        ),
        "The words bungalow and shampoo originate from the languages of which country?" to mutableListOf(
            "India",
            "Papua New Guinea",
            "Ethiopia",
            "China"
        ),
        "What ingredient is NOT used to craft a cake in Minecraft?" to mutableListOf(
            "Bread",
            "Wheat",
            "Milk",
            "Egg"
        ),
        "What was Manfred von Richthofens nickname?" to mutableListOf(
            "The Red Baron",
            "The High Flying Ace",
            "The Blue Serpent ",
            "The Germany Gunner"
        ),
        "The 2014 movie The Raid 2: Berandal was mainly filmed in which Asian country?" to mutableListOf(
            "Indonesia",
            "Thailand",
            "Brunei",
            "Malaysia"
        ),
        "What polymer is used to make CDs, safety goggles and riot shields?" to mutableListOf(
            "Polycarbonate",
            "Rubber",
            "Nylon",
            "Bakelite"
        ),
        "What is the Swedish word for window?" to mutableListOf("Fnster", "Hl", "Skrm", "Ruta"),
        "Who was the main antagonist of Max Payne 3 (2012)?" to mutableListOf(
            "Victor Branco",
            "lvaro Neves",
            "Armando Becker",
            "Milo Rego"
        ),
        "The song Feel Good Inc. by British band Gorillaz features which hip hop group?" to mutableListOf(
            "De La Soul",
            "Public Enemy",
            "OutKast",
            "Cypress Hill"
        ),
        "In Cook, Serve, Delicious!, which food is NOT included in the game?" to mutableListOf(
            "Pie",
            "Shish Kabob",
            "Hamburger",
            "Lasagna"
        ),
        "Which of these television shows makes everyone look under their chair?" to mutableListOf(
            "Oprah",
            "Jimmy Fallon",
            "Saturday Night Live",
            "Larry Rubert"
        ),
        "Which female player won the gold medal of table tennis singles in 2016 Olympics Games?" to mutableListOf(
            "DING Ning (China)",
            "LI Xiaoxia (China)",
            "Ai FUKUHARA (Japan)",
            "Song KIM (North Korea)"
        ),
        "All the Boys by Panic! At the Disco was released as a bonus track on what album?" to mutableListOf(
            "Too Weird To Live, Too Rare To Die!",
            "A Fever You Cant Sweat Out",
            "Death Of A Bachelor",
            "Vices  Virtues"
        ),
        "How many women joined the United States Armed Services during World War II?" to mutableListOf(
            "350,000",
            "225,000",
            "100,000",
            "500,000"
        ),
        "In World War I, what was the name of the alliance of Germany, Austria-Hungary, the Ottoman Empire, and Bulgaria?" to mutableListOf(
            "The Central Powers",
            "The Axis Powers",
            "The Federation of Empires",
            "Authoritarian Alliance"
        ),
        "Which of the following cellular device companies is NOT headquartered in Asia?" to mutableListOf(
            "Nokia",
            "LG Electronics",
            "Samsung",
            "HTC"
        ),
        "Which of these stages is not playable in Super Smash Bros. for Wii U?" to mutableListOf(
            "Fountain of Dreams",
            "Bridge of Eldin",
            "75m",
            "Miiverse"
        ),
        "In the Call Of Duty: Zombies map Origins, where is Stamin-Up located?" to mutableListOf(
            "Generator 5",
            "Generator 3",
            "Generator 4",
            "Excavation Site"
        ),
        "In Battlestar Galactica (2004), what is the name of the President of the Twelve Colonies?" to mutableListOf(
            "Laura Roslin",
            "William Adama",
            "Tricia Helfer",
            "Harry Stills"
        ),
        "In Left 4 Dead, which campaign has the protagonists going through a subway in order to reach a hospital for evacuation?" to mutableListOf(
            "No Mercy",
            "Subway Sprint",
            "Hospital Havoc",
            "Blood Harvest"
        ),
        "In which fast food chain can you order a Jamocha Shake?" to mutableListOf(
            "Arbys",
            "McDonalds",
            "Burger King",
            "Wendys"
        ),
        "Which is NOT a book in the Harry Potter Series?" to mutableListOf(
            "The House Elf",
            "The Chamber of Secrets",
            "The Prisoner of Azkaban",
            "The Deathly Hallows"
        ),
        "What vault in the video game Fallout 3 is the home of multiple clones named Gary?" to mutableListOf(
            "Vault 108",
            "Vault 101",
            "Vault 87",
            "Vault 21"
        ),
        "Which song previously recorded by Elvis Presley was covered by UB40 with a reggae beat?" to mutableListOf(
            "Cant Help Falling in Love",
            "Jailhouse Rock",
            "In the Ghetto",
            "Wooden Heart"
        ),
        "What is the name of a rabbits abode?" to mutableListOf("Burrow", "Nest", "Den", "Dray"),
        "Which of these facilities was not present on the Titanic?" to mutableListOf(
            "Fainting room",
            "Turkish bath",
            "Kennel",
            "Squash court"
        ),
        "What animated internet character is known to answer emails with his boxing gloves?" to mutableListOf(
            "Strong Bad",
            "Strong Sad",
            "Strong Mad",
            "Strong Glad"
        ),
        "In Terraria, which debuff does the ankh charm not provide immunity to?" to mutableListOf(
            "Venom",
            "Cursed",
            "Bleeding",
            "Slow"
        ),
        "What is Solid Snakes real name?" to mutableListOf("David", "Solid Snake", "John", "Huey"),
        "How many zeros are there in a googol?" to mutableListOf("100", "10", "1,000", "1,000,000"),
        "What historical event was Tchaikovskys 1812 Overture referencing?" to mutableListOf(
            "The Napoleonic Wars",
            "The American War of 1812",
            "The Russian Revolution",
            "The Charge of the Light Brigade (Crimean War)"
        ),
        "Which franchise does the creature Slowpoke originate from?" to mutableListOf(
            "Pokemon",
            "Dragon Ball",
            "Sonic The Hedgehog",
            "Yugioh"
        ),
        "Who is the protagonist in the game The Walking Dead: Season One?" to mutableListOf(
            "Lee Everett",
            "Clementine",
            "Kenny",
            "Rick Grimes"
        ),
        "What is the name of one of the Neo-Aramaic languages spoken by the Jewish population from Northwestern Iraq?" to mutableListOf(
            "Lishana Deni",
            "Hulaul",
            "Lishan Didan",
            "Chaldean Neo-Aramaic"
        ),
        "Which of these is NOT a playable character in the 2016 video game Overwatch?" to mutableListOf(
            "Invoker",
            "Mercy",
            "Winston",
            "Zenyatta"
        ),
        "Which movie includes a giant bunny-like spirit who has magic powers including growing trees?" to mutableListOf(
            "My Neighbor Totoro",
            "Hop",
            "Rise of the Guardians",
            "Alice in Wonderland"
        ),
        "Where did the dog breed Chihuahua originate?" to mutableListOf(
            "Mexico",
            "France",
            "Spain",
            "Russia"
        ),
        "Which of these Counter-Strike maps is a bomb defuse scenario?" to mutableListOf(
            "Prodigy",
            "747",
            "Havana",
            "Oilrig"
        ),
        "When was the SS or Schutzstaffel established?" to mutableListOf(
            "April 4th, 1925",
            "September 1st, 1941",
            "March 8th, 1935",
            "February 21st, 1926"
        ),
        "What year was the game Overwatch revealed?" to mutableListOf(
            "2014",
            "2015",
            "2011",
            "2008"
        ),
        "In what Half-Life expansion can you find Gordons picture in an Employee of the Month picture frame?" to mutableListOf(
            "Half-Life: Opposing Force",
            "Half-Life: Blue Shift",
            "Half-Life: Decay",
            "They Hunger"
        ),
        "The main protagonist of the fifth part of JoJos Bizarre Adventure is which of the following?" to mutableListOf(
            "Giorno Giovanna",
            "Guido Mista",
            "Jonathan Joestar",
            "Joey JoJo"
        ),
        "Which one of these action movies are shot entirely in one take?" to mutableListOf(
            "Victoria",
            "Ip Man 2",
            "The Bourne Legacy",
            "Lon: The Professional"
        ),
        "What nationality was sultan Saladin?" to mutableListOf(
            "Kurdish",
            "Arab",
            "Egyptian",
            "Syrian"
        ),
        "Sting, the lead vocalist of The Police, primarily plays what instrument?" to mutableListOf(
            "Bass Guitar",
            "Drums",
            "Guitar",
            "Keyboards"
        ),
        "In Danganronpa: Trigger Happy Havoc, what is the protagonists name?" to mutableListOf(
            "Makoto Naegi",
            "Hajime Hinata",
            "Nagito Komaeda",
            "Komaru Naegi"
        ),
        "The protagonist in the game Cave Story is named" to mutableListOf(
            "Quote",
            "Kazuma",
            "Curly",
            "Arthur"
        ),
        "Who made the discovery of X-rays?" to mutableListOf(
            "Wilhelm Conrad Rntgen",
            "Thomas Alva Edison",
            "James Watt",
            "Albert Einstein"
        ),
        "Which song in Drakes Views features Future?" to mutableListOf(
            "Grammys",
            "Too Good",
            "Faithful",
            "Pop Style"
        ),
        "This weapon in Counter-Strike: Global Offensive does not exist in real life." to mutableListOf(
            "M4A4",
            "AWP",
            "M4A1",
            "MP9"
        ),
        "Who was the first prime minister of Canada?" to mutableListOf(
            "John Macdonald",
            "John Abbott",
            "Alexander Mackenzie",
            "Robert Borden"
        ),
        "What is the oldest team in Major League Baseball?" to mutableListOf(
            "Atlanta Braves",
            "Chicago Cubs",
            "Cincinnati Reds",
            "St. Louis Cardinals"
        ),
        "What minimum level in the Defence skill is needed to equip Dragon Armour in the MMO RuneScape?" to mutableListOf(
            "60",
            "65",
            "55",
            "70"
        ),
        "Aoi Miyamori is the production manager of what anime in Shirobako?" to mutableListOf(
            "The Third Aerial Girls Squad",
            "Exodus!",
            "Andes Chucky",
            "Angel Beats!"
        ),
        "What was studio Triggers first original long-form animated series for television?" to mutableListOf(
            "Kill la Kill",
            "Kiznaiver",
            "Inferno Cop",
            "Gurren Lagann"
        ),
        "Which country was an allied power in World War II?" to mutableListOf(
            "Soviet Union",
            "Italy",
            "Germany",
            "Japan"
        ),
        "Which of the following was the author of Username Evie?" to mutableListOf(
            "Joe Sugg",
            "Zoe Sugg",
            "Joe Weller",
            "Alfie Deyes"
        ),
        "Which car manufacturer won the 2017 24 Hours of Le Mans?" to mutableListOf(
            "Porsche",
            "Toyota",
            "Audi",
            "Chevrolet"
        ),
        "In what sport does Fanny Chmelar compete for Germany?" to mutableListOf(
            "Skiing",
            "Swimming",
            "Showjumping",
            "Gymnastics"
        ),
        "Which churchs interior in Vatican City was designed in 1503 by renaissance architects including Bramante, Michelangelo and Bernini?" to mutableListOf(
            "St. Peters Basilica",
            "Catania Cathedral",
            "St. Marks Basilica",
            "The Duomo of Florence"
        ),
        "What does Bart sell his soul for in The Simpsons episode Bart Sells His Soul?" to mutableListOf(
            "$5",
            "A Copy of Bonestorm 2",
            "$100",
            "A Giant Gobstopper"
        ),
        "What is the Linnean name of the domestic apple tree?" to mutableListOf(
            "Malus pumila",
            "Malus americana",
            "Pomus domestica",
            "Appelus delectica"
        ),
        "Which of the following Zelda games did not feature Ganon as a final boss?" to mutableListOf(
            "Majoras Mask",
            "Ocarina of Time",
            "Skyward Sword",
            "Breath of the Wild"
        ),
        "When was the first mammal successfully cloned?" to mutableListOf(
            "1996",
            "2009",
            "1999",
            "1985"
        ),
        "The formerly East-Prussian city of Knigsberg is known as which Russian City today?" to mutableListOf(
            "Kaliningrad",
            "Kazan",
            "Kursk",
            "Krasnodar"
        ),
        "GoldenEye 007 on the Nintendo 64 was planned to allow you to play as all previous Bond actors, with the exception of who?" to mutableListOf(
            "George Lazenby",
            "Roger Moore",
            "Sean Connery",
            "Timothy Dalton"
        ),
        "According to the International System of Units, how many bytes are in a kilobyte of RAM?" to mutableListOf(
            "1000",
            "512",
            "1024",
            "500"
        ),
        "Who wrote the musical composition, Rhapsody In Blue?" to mutableListOf(
            "George Gershwin",
            "Irving Berlin",
            "Duke Ellington",
            "Johnny Mandel"
        ),
        "In the 9th Pokemon movie, who is the Prince of the Sea?" to mutableListOf(
            "Manaphy",
            "Ash",
            "May",
            "Phantom"
        ),
        "What scientific suborder does the family Hyaenidae belong to?" to mutableListOf(
            "Feliformia",
            "Haplorhini",
            "Caniformia",
            "Ciconiiformes"
        ),
        "Who is the true moon princess in Sailor Moon?" to mutableListOf(
            "Sailor Moon",
            "Sailor Venus",
            "Sailor Mars",
            "Sailor Jupiter"
        ),
        "What did the Spanish autonomous community of Catalonia ban in 2010, that took effect in 2012?" to mutableListOf(
            "Bullfighting",
            "Fiestas",
            "Flamenco",
            "Mariachi"
        ),
        "Which Italian automobile manufacturer gained majority control of U.S. automobile manufacturer Chrysler in 2011?" to mutableListOf(
            "Fiat",
            "Maserati",
            "Alfa Romeo",
            "Ferrari"
        ),
        "What is the German word for spoon?" to mutableListOf(
            "Lffel",
            "Gabel",
            "Messer",
            "Essstbchen"
        ),
        "Which one of these is not an official development name for a Ubuntu release?" to mutableListOf(
            "Mystic Mansion",
            "Trusty Tahr",
            "Utopic Unicorn",
            "Wily Werewolf"
        ),
        "In what year were achivements added to Steam?" to mutableListOf(
            "2007",
            "2008",
            "2009",
            "2006"
        ),
        "Which one of these songs did the group Men At Work NOT make?" to mutableListOf(
            "Safety Dance",
            "Down Under",
            "Who Can It Be Now?",
            "Its a Mistake"
        ),
        "What is the cartoon character, Andy Capp, known as in Germany?" to mutableListOf(
            "Willi Wakker",
            "Dick Tingeler",
            "Helmut Schmacker",
            "Rod Tapper"
        ),
        "Which of these is not an Ed Sheeran album?" to mutableListOf("-", "+", "X", ""),
        "Which of the following manga have the most tankouban volumes?" to mutableListOf(
            "Golgo 13",
            "JoJos Bizarre Adventure",
            "Detective Conan",
            "One Piece"
        ),
        "What team won the 2016 MLS Cup?" to mutableListOf(
            "Seattle Sounders",
            "Colorado Rapids",
            "Toronto FC",
            "Montreal Impact"
        ),
        "Which animated film did Steven Lisberger direct in 1980 before going on to direct Tron?" to mutableListOf(
            "Animalympics",
            "The Fox and the Hound",
            "The Black Cauldron",
            "The Great Mouse Detecive"
        ),
        "In the Pokmon series, what is Palkias hidden ability?" to mutableListOf(
            "Telepathy",
            "Pressure",
            "Water Bubble",
            "Hydration"
        ),
        "When was the game Roblox released?" to mutableListOf("2006", "2003", "2002", "2007"),
        "British actor David Morrissey stars as which role in The Walking Dead?" to mutableListOf(
            "The Governor",
            "Negan",
            "Rick Grimes",
            "Daryl Dixon"
        ),
        "Which of following is rude and dishonorable by Klingon standards?" to mutableListOf(
            "Taking his Dk tahg",
            "Insulting and laughing at him at the dinner table",
            "Reaching over and taking his meal",
            "Punching him and taking his ship station position"
        ),
        "What game was used to advertise Steam?" to mutableListOf(
            "Counter-Strike 1.6",
            "Half-Life",
            "Half-Life 2",
            "Team Fortress"
        ),
        "Which rapper had an album that went double platinum with no features?" to mutableListOf(
            "J. Cole",
            "Kendrick Lamar",
            "Drake",
            "Big Sean"
        ),
        "What is the alter-ego of the DC comics character Superman?" to mutableListOf(
            "Clark Kent",
            "Bruce Wayne",
            "Arthur Curry",
            "John Jones"
        ),
        "Which of these is the only fighter in the game Super Smash Bros. Melee capable of dealing damage with their taunt animation?" to mutableListOf(
            "Luigi",
            "Mr. Game  Watch",
            "Jigglypuff",
            "Pichu"
        ),
        "What is real haggis made of?" to mutableListOf(
            "Sheeps Heart, Liver and Lungs",
            "Sheeps Heart, Kidneys and Lungs",
            "Sheeps Liver, Kidneys and Eyes",
            "Whole Sheep"
        ),
        "In 2013, virtual pop-star Hatsune Miku had a sponsorship with which pizza chain?" to mutableListOf(
            "Dominos",
            "Papa Johns",
            "Pizza Hut",
            "Sabarros"
        ),
        "Exile and Revelations were the third and fourth installments of which PC game series?" to mutableListOf(
            "Myst",
            "Shivers",
            "Doom",
            "Tropico"
        ),
        "What is the name of Chriss brother in Everybody Hates Chris?" to mutableListOf(
            "Drew",
            "Jerome",
            "Greg",
            "Joe"
        ),
        "Gabe Newell was born in which year?" to mutableListOf("1962 ", "1970", "1960", "1972"),
        "Which of the following was Brazil was a former colony under?" to mutableListOf(
            "Portugal",
            "Spain",
            "The Netherlands",
            "France"
        ),
        "What was the name of Jonnys pet dog in The Adventures of Jonny Quest?" to mutableListOf(
            "Bandit",
            "Lucky",
            "Rocky",
            "Max"
        ),
        "In Kingdom Hearts, what is the name of Soras home world?" to mutableListOf(
            "Destiny Islands",
            "Agrabah",
            "Land of Departure",
            "Disney Town"
        ),
        "What year did the effort to deploy the Common Core State Standards (CCSS) in the US begin?" to mutableListOf(
            "2009",
            "2012",
            "2006",
            "1997"
        ),
        "Which of the following blood component forms a plug at the site of injuries?" to mutableListOf(
            "Platelets",
            "Red blood cells",
            "White blood cells",
            "Blood plasma"
        ),
        "Which country did Kabaddi, a contact sport involving tackling, originate from?" to mutableListOf(
            "India",
            "Australia",
            "Turkey",
            "Cambodia"
        ),
        "Who is Batman?" to mutableListOf("Bruce Wayne", "Clark Kent", "Barry Allen", "Tony Stark"),
        "Which company did Gabe Newell work at before founding Valve Corporation?" to mutableListOf(
            "Microsoft",
            "Apple",
            "Google",
            "Yahoo"
        ),
        "Which state of the United States is the smallest?" to mutableListOf(
            "Rhode Island ",
            "Maine",
            "Vermont",
            "Massachusetts"
        ),
        "Which of these is NOT a map included in the game Counter-Strike: Global Offensive?" to mutableListOf(
            "Oilrig",
            "Assault",
            "Mirage",
            "Cache"
        ),
        "Just Cause 2 was mainly set in what fictional Southeast Asian island country?" to mutableListOf(
            "Panau",
            "Davao",
            "Macau",
            "Palau"
        ),
        "How many planets are in our Solar System?" to mutableListOf(
            "Eight",
            "Nine",
            "Seven",
            "Ten"
        ),
        "What five letter word is the motto of the IBM Computer company?" to mutableListOf(
            "Think",
            "Click",
            "Logic",
            "Pixel"
        ),
        "On what day did Germany invade Poland?" to mutableListOf(
            "September 1, 1939",
            "December 7, 1941",
            "June 22, 1941",
            "July 7, 1937"
        ),
        "Which of the following James Bond villains is not affiliated with the SPECTRE organization?" to mutableListOf(
            "Auric Goldfinger",
            "Dr. Julius No",
            "Rosa Klebb",
            "Emilio Largo"
        ),
        "Donald J. Trumps Middle Name is..." to mutableListOf("John", "Jeff", "Jerald", "Jason"),
        "Which Russian oblast forms a border with Poland?" to mutableListOf(
            "Kaliningrad",
            "Samara",
            "Nizhny Novgorod",
            "Omsk"
        ),
        "This Greek goddesss name was chosen for the dwarf planet responsible for discord on Plutos classification amongst astronomers." to mutableListOf(
            "Eris",
            "Charon",
            "Ceres",
            "Dysnomia"
        ),
        "Folic acid is the synthetic form of which vitamin?" to mutableListOf(
            "Vitamin B",
            "Vitamin A",
            "Vitamin C",
            "Vitamin D"
        ),
        "What is the name of the popular animatronic singing fish prop, singing such hits such as Dont Worry, Be Happy?" to mutableListOf(
            "Big Mouth Billy Bass",
            "Big Billy Bass",
            "Singing Fish",
            "Sardeen"
        ),
        "During the Wars of the Roses (1455 - 1487) which Englishman was dubbed the Kingmaker?" to mutableListOf(
            "Richard Neville",
            "Richard III",
            "Henry V",
            "Thomas Warwick"
        ),
        "Which of these characters is the mascot of the video game company SEGA?" to mutableListOf(
            "Sonic the Hedgehog",
            "Dynamite Headdy",
            "Alex Kidd",
            "Opa-Opa"
        ),
        "Llanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogoch is located on which Welsh island?" to mutableListOf(
            "Anglesey",
            "Barry",
            "Bardsey",
            "Caldey"
        ),
        "The Hunua Ranges is located in..." to mutableListOf(
            "New Zealand",
            "Nepal",
            "China",
            "Mexico"
        ),
        "Which Pokemon generation did the fan-named Masuda Method first appear in? " to mutableListOf(
            "Diamond/Pearl",
            "Ruby/Sapphire",
            "Black/White",
            "X/Y"
        ),
        "What musician made the song Fuckin` Perfect in 2010?" to mutableListOf(
            "P!nk",
            "Mitis",
            "Adam lambert",
            "Koven"
        ),
        "What caused the titular mascot of Yo-Kai Watch, Jibanyan, to become a yokai?" to mutableListOf(
            "Being run over by a truck",
            "Ate one too many chocobars",
            "Through a magical ritual",
            "When he put on the harmaki"
        ),
        "In Formula 1, the Virtual Safety Car was introduced following the fatal crash of which driver?" to mutableListOf(
            "Jules Bianchi",
            "Ayrton Senna",
            "Ronald Ratzenberger",
            "Gilles Villeneuve"
        ),
        "The heroine of Humanity Has Declined is a mediator between humans and what?" to mutableListOf(
            "Fairies",
            "Elves",
            "The Earth",
            "Animals"
        ),
        "In A Certain Magical Index, what is Accelerator able to control?" to mutableListOf(
            "Vectors",
            "Velocity",
            "Quantums",
            "Wormholes"
        ),
        "What year did Attack on Titan first air?" to mutableListOf("2013", "2014", "2012", "2015"),
        "Which programming language shares its name with an island in Indonesia?" to mutableListOf(
            "Java",
            "Python",
            "C",
            "Jakarta"
        ),
        "When Magic: The Gathering was first solicited, which of the following was it originally titled?" to mutableListOf(
            "Mana Clash",
            "Magic",
            "Magic Clash",
            "Mana Duels"
        ),
        "In Calvin and Hobbes, what is the name of the babysitters boyfriend?" to mutableListOf(
            "Charlie",
            "Dave",
            "Charles",
            "Nathaniel"
        ),
        "By definition, where does an abyssopelagic animal live?" to mutableListOf(
            "At the bottom of the ocean",
            "In the desert",
            "On top of a mountain",
            "Inside a tree"
        ),
        "What is the collective noun for vultures?" to mutableListOf(
            "Wake",
            "Ambush",
            "Building",
            "Gaze"
        ),
        "What major programming language does Unreal Engine 4 use?" to mutableListOf(
            "C++",
            "Assembly",
            "C#",
            "ECMAScript"
        ),
        "Who assassinated President James A. Garfield?" to mutableListOf(
            "Charles Guiteau",
            "Sirhan Sirhan",
            "Leon Czolgosz",
            "John Wilkes Booth"
        ),
        "In the game Warframe, what Mastery Rank do you need to have to build the Tigris Prime?" to mutableListOf(
            "13",
            "6",
            "18",
            "10"
        ),
        "In World of Warcraft the default UI color that signifies Druid is what?" to mutableListOf(
            "Orange",
            "Brown",
            "Green",
            "Blue"
        ),
        "Which of the following Physicists aided Nazi Germany in their production of a nuclear weapon?" to mutableListOf(
            "Werner Heisenberg",
            "John von Neumann",
            "Albert Einstein",
            "Max Planck"
        ),
        "Whats the race of Invincibles father?" to mutableListOf(
            "Viltrumite",
            "Kryptonian",
            "Kree",
            "Irken"
        ),
        "In Resident Evil 3, how many inventory slots does Jill have at the start of the game?" to mutableListOf(
            "10",
            "6",
            "8",
            "12"
        ),
        "Which company was established on April 1st, 1976 by Steve Jobs, Steve Wozniak and Ronald Wayne?" to mutableListOf(
            "Apple",
            "Microsoft",
            "Atari",
            "Commodore"
        ),
        "What is the approximate value of mathematical constant e?" to mutableListOf(
            "2.72",
            "3.14",
            "1.62",
            "1.41"
        ),
        "Which French duo had UK hits in 1998 with the songs Sexy Boy, Kelly Watch The Stars  All I Need?" to mutableListOf(
            "Air",
            "Fire",
            "Earth",
            "Water"
        ),
        "What was the name of the WWF professional wrestling tag team made up of the wrestlers Ax and Smash?" to mutableListOf(
            "Demolition",
            "The Dream Team",
            "The Bushwhackers",
            "The British Bulldogs"
        ),
        "Who had a 1969 top 5 hit with the song,  A Boy Named Sue?" to mutableListOf(
            "Johnny Cash",
            "Bob Dylan",
            "Willie Nelson",
            "Kris Kristofferson"
        ),
        "Along with Gabe Newell, who co-founded Valve?" to mutableListOf(
            "Mike Harrington",
            "Robin Walker",
            "Marc Laidlaw",
            "Stephen Bahl"
        ),
        "Half-Life by Valve uses the GoldSrc game engine, which is a highly modified version of what engine?" to mutableListOf(
            "Quake Engine",
            "Doom Engine",
            "id Engine",
            "Source Engine"
        ),
        "Whose signature guitar technique is called the windmill?" to mutableListOf(
            "Pete Townshend",
            "Jimmy Page",
            "Eddie Van Halen",
            "Jimi Hendrix"
        ),
        "In which countrys version of Half-Life are the HECU Marines replaced with robots?" to mutableListOf(
            "Germany",
            "Japan",
            "China",
            "France"
        ),
        "Which of the following is NOT a Russian car manufacturer?" to mutableListOf(
            "BYD",
            "Silant",
            "Dragon",
            "GAZ"
        ),
        "What was the name of the first Robin in the Batman comics?" to mutableListOf(
            "Dick Grayson",
            "Bruce Wayne",
            "Jason Todd",
            "Tim Drake"
        ),
        "Which US state has the highest population?" to mutableListOf(
            "California",
            "New York",
            "Texas",
            "Florida"
        ),
        "What year did radio icon Howard Stern start a job at radio station WNBC?" to mutableListOf(
            "1982",
            "1985",
            "1984",
            "1995"
        ),
        "What is the stage name of English female rapper Mathangi Arulpragasam, who is known for the song Paper Planes?" to mutableListOf(
            "M.I.A.",
            "K.I.A.",
            "C.I.A.",
            "A.I.A."
        ),
        "What year did the game Overwatch enter closed beta?" to mutableListOf(
            "2015",
            "2013",
            "2011",
            "2016"
        ),
        "The stop motion comedy show Robot Chicken was created by which of the following?" to mutableListOf(
            "Seth Green",
            "Seth MacFarlane",
            "Seth Rogen",
            "Seth Rollins"
        ),
        "What is the Capital of the United States?" to mutableListOf(
            "Washington, D.C.",
            "Los Angelas, CA",
            "New York City, NY",
            "Houston, TX"
        ),
        "Who is the leader of Team Instinct in Pokmon Go?" to mutableListOf(
            "Spark",
            "Candela",
            "Blanche",
            "Willow"
        ),
        "When was Chapter 1 of the Source Engine mod Underhell released?" to mutableListOf(
            "September 1st, 2013",
            "March 3rd, 2011",
            "September 12th, 2013",
            "October 2nd, 2012"
        ),
        "Which episode from The Amazing World Of Gumball won the Childrens Choice Award at the British Animation Awards in 2016?" to mutableListOf(
            "The Shell",
            "The Limit",
            "The Kids",
            "The Gripes"
        ),
        "Who are the original creators of Rachet  Clank?" to mutableListOf(
            "Insomniac Games",
            "PixelTail Games",
            "Rare",
            "Bethesda"
        ),
        "When was Left 4 Dead 2 released?" to mutableListOf(
            "November 17, 2009",
            "May 3, 2008",
            "November 30, 2009",
            "June 30, 2010"
        ),
        "Minecraft was released from beta in 2011 during a convention held in which city?" to mutableListOf(
            "Las Vegas",
            "Paris",
            "Bellevue",
            "London"
        ),
        "Which of these characters from SpongeBob SquarePants is not a squid?" to mutableListOf(
            "Gary",
            "Orvillie",
            "Squidward",
            "Squidette"
        ),
        "Which Perk-A-Cola in Call Of Duty: Zombies was reworked in Call of Duty: Black Ops II?" to mutableListOf(
            "Double Tap Root Beer",
            "Whos Who",
            "Juggernog",
            "Mule Kick"
        ),
        "In Mulan (1998), who is the leader of the Huns?" to mutableListOf(
            "Shan Yu",
            "Chien-Po",
            "Li Shang",
            "Fa Zhou"
        ),
        "Which of the following men does not have a chemical element named after him?" to mutableListOf(
            "Sir Isaac Newton",
            "Albert Einstein",
            "Niels Bohr",
            "Enrico Fermi"
        ),
        "When someone is inexperienced they are said to be what color?" to mutableListOf(
            "Green",
            "Red",
            "Blue",
            "Yellow"
        ),
        "Which of the following famous mathematicians died in a duel at the age of 20?" to mutableListOf(
            "Galois",
            "Abel",
            "Euler",
            "Gauss"
        ),
        "Which greek god/goddess tossed a golden apple with the words for the fairest into the middle of the feast of the gods?" to mutableListOf(
            "Eris",
            "Hades",
            "Ares",
            "Artemis"
        ),
        "What was the punishment for Sysiphuss craftiness?" to mutableListOf(
            "Cursed to roll a boulder up a hill for eternity.",
            "Tied to a boulder for eternity, being pecked by birds.",
            "Standing in a lake filled with water he could not drink.",
            "To fell a tree that regenerated after every axe swing."
        ),
        "What is the colour of unoxidized blood?" to mutableListOf(
            "Red",
            "Blue",
            "Purple",
            "Green"
        ),
        "In the game The Sims, how many Simoleons does each family start with?" to mutableListOf(
            "20,000",
            "10,000",
            "15,000",
            "25,000"
        ),
        "Which of these is NOT a city in Saudi Arabia?" to mutableListOf(
            "Dubai",
            "Riyadh",
            "Mecca",
            "Medina"
        ),
        "Gannymede is the largest moon of which planet?" to mutableListOf(
            "Jupiter",
            "Uranus",
            "Neptune",
            "Mars"
        ),
        "Who is the villain company in Stardew Valley?" to mutableListOf(
            "Joja Co ",
            "Ronin",
            "Empire",
            "Robotnik Industrys "
        ),
        "All of the following countries have official claims to territory in Antartica EXCEPT:" to mutableListOf(
            "United States",
            "Australia",
            "Chile",
            "Norway"
        ),
        "Which of these artists do NOT originate from France?" to mutableListOf(
            "The Chemical Brothers",
            "Air",
            "Justice",
            "Daft Punk"
        ),
        "In Sonic the Hedgehog 2 for the Sega Genesis, what do you input in the sound test screen to access the secret level select?" to mutableListOf(
            "The Lead Programmers birthday",
            "The first release date of Sonic the Hedgehog",
            "The date Sonic Team was founded",
            "The first release date of Sonic the Hedgehog 2"
        ),
        "What is the last name of Edward and Alphonse in the Fullmetal Alchemist series." to mutableListOf(
            "Elric",
            "Ellis",
            "Eliek",
            "Elwood"
        ),
        "Which of these Worms games featured 3D gameplay?" to mutableListOf(
            "Worms 4: Mayhem",
            "Worms W.M.D",
            "Worms Reloaded",
            "Worms: Open Warfare 2"
        ),
        "The main antagonist of the second part of JoJos Bizarre Adventure is which of the following?" to mutableListOf(
            "Kars",
            "Erina Joestar",
            "Santana",
            "Wired Beck"
        ),
        "What country is Cory in the House set in?" to mutableListOf(
            "The United States of America",
            "Canada",
            "Venezuala",
            "England"
        ),
        "In the Fallout series, on which date did The Great War happen?" to mutableListOf(
            "October 23rd, 2077",
            "May 15th, 2058",
            "December 14th, 2069",
            "November 5th, 2076"
        ),
        "Which of these weapon classes DO NOT appear in the first Monster Hunter game?" to mutableListOf(
            "Bow ",
            "Hammer",
            "Heavy Bowgun",
            "Light Bowgun"
        ),
        "Which Nation DID NOT have a Colony in Modern-day America?" to mutableListOf(
            "Portugal",
            "Spain",
            "Sweden",
            "Netherlands"
        ),
        "Which class of animals are newts members of?" to mutableListOf(
            "Amphibian",
            "Fish",
            "Reptiles",
            "Mammals"
        ),
        "Which of these characters was considered, but ultimately not included, for Super Smash Bros. Melee?" to mutableListOf(
            "James Bond",
            "Diddy Kong",
            "Mega Man",
            "Wave Racer"
        ),
        "What was the first ever London Underground line to be built?" to mutableListOf(
            "Metropolitan Line",
            "Circle Line",
            "Bakerloo Line",
            "Victoria Line"
        ),
        "The character Plum from No Game No Life is of what race?" to mutableListOf(
            "Dhampir",
            "Flgel",
            "Imanity",
            "Seiren"
        ),
        "Which company developed the Hololens?" to mutableListOf(
            "Microsoft",
            "HTC",
            "Oculus",
            "Tobii"
        ),
        "What year did the Chevrolet LUV mini truck make its debut?" to mutableListOf(
            "1972",
            "1982",
            "1975",
            "1973"
        ),
        "Which of these songs is not on the untitled album by Led Zeppelin?" to mutableListOf(
            "Celebration Day",
            "Stairway to Heaven",
            "Black Dog",
            "Rock and Roll"
        ),
        "What is the make and model of the tour vehicles in Jurassic Park (1993)?" to mutableListOf(
            "1992 Ford Explorer XLT",
            "1992 Toyota Land Cruiser",
            "1992 Jeep Wrangler YJ Sahar",
            "Mercedes M-Class"
        ),
        "In season one of the US Kitchen Nightmares, Gordan Ramsay tried to save 10 different restaurants. How many ended up closing afterwards?" to mutableListOf(
            "9",
            "6",
            "3",
            "0"
        ),
        "How old was Adolf Hitler when he died?" to mutableListOf("56", "43", "65", "47"),
        "In Magic: The Gathering, during the design for Planar Chaos, what color did the developers think of adding in as the sixth color?" to mutableListOf(
            "Purple",
            "Brown",
            "Pink",
            "Orange"
        ),
        "Which country has the most Trappist breweries?" to mutableListOf(
            "Belgium",
            "Netherlands",
            "France",
            "USA"
        ),
        "What zodiac sign is represented by a pair of scales?" to mutableListOf(
            "Libra",
            "Aries",
            "Capricorn",
            "Sagittarius"
        ),
        "What is the name for a male bee that comes from an unfertilized egg?" to mutableListOf(
            "Drone",
            "Soldier",
            "Worker",
            "Male"
        ),
        "In Disneys Toontown Online, which of these species wasnt available as a Toon?" to mutableListOf(
            "Cow",
            "Monkey",
            "Bear",
            "Pig"
        ),
        "Which city features all of their professional sports teams jerseys with the same color scheme?" to mutableListOf(
            "Pittsburgh",
            "New York",
            "Seattle",
            "Tampa Bay"
        ),
        "Which major extinction event was caused by an asteroid collision and eliminated the majority of non-avian dinosaurs?" to mutableListOf(
            "Cretaceous-Paleogene",
            "TriassicJurassic",
            "PermianTriassic",
            "OrdovicianSilurian"
        ),
        "Which of these American cities has fewer than 1,000,000 people?" to mutableListOf(
            "San Francisco, California",
            "Phoenix, Arizona",
            "San Antonio, Texas",
            "Philadelphia, Pennsylvania"
        ),
        "What island in the Canary Islands was the scene of one of the worst air disasters in history with the collision of two jumbo jets?" to mutableListOf(
            "Tenerife",
            "Fuerteventura",
            "Gran Canaria",
            "Maui"
        ),
        "In the 2014 Pokemon VGC Finals, which Pokemon was famous for bringing the winner to victory?" to mutableListOf(
            "Pachirisu",
            "Garchomp",
            "Lapras",
            "Primal Groudon"
        )
    )
    val random = Random
    return dictionary.entries.elementAt(random.nextInt(dictionary.size))

}

fun randomTextToButton(button: Button, map: Map.Entry<String, MutableList<String>>) {
    val random = (0 until map.value.size).random()
    button.text = map.value[random]
    map.value.removeAt(random)
}
