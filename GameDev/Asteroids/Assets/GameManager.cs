using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;
/**
 * Author @Nicole Colgan 19345826
 **/

public class GameManager : MonoBehaviour
{
    // Start is called before the first frame update

    public GameObject asteroidPrefab, spaceShip, bullet, menue, playing, startButton, scoreValue, highScoreValue;
    public int numBullets = 0;
    public static Vector3 screenBottomLeft, screenTopRight;
    public static float screenWidth, screenHeight;
    public static int currentGameLevel, score, highscore, index, numTimesClicked;
    public static int lives = 3;
    public List<GameObject> asteroids = new List<GameObject>();


    void Start()
    {
        screenBottomLeft = Camera.main.ViewportToWorldPoint(new Vector3(0, 0, 30));
        screenTopRight = Camera.main.ViewportToWorldPoint(new Vector3(1, 1, 30));
        screenWidth = screenTopRight.x - screenBottomLeft.x;
        screenHeight = screenTopRight.z - screenBottomLeft.z;
        //set so camera is positioned at (0,30,0)
        currentGameLevel = 0;
        Camera.main.transform.position = new Vector3(0, 30, 0);
        //facing toward (0,0,0) with (0,0,1) as its up axis
        Camera.main.transform.LookAt(new Vector3(0, 0, 0), new Vector3(0, 0, 1));
        //when start button is clicked, switch to playing state and switch the GUI benig displayed and
        //call start new game
        //get menus child
        score = 0;
        highscore = 0;
        index = 0;
        numTimesClicked = 0;
        Button btn = startButton.GetComponent<Button>();
        btn.onClick.AddListener(StartButtonClicked);
        menue = GameObject.FindGameObjectWithTag("Menu");
        playing = GameObject.FindGameObjectWithTag("Playing");
        playing.SetActive(false);   //playing state originally off
    }

    // Update is called once per frame
    void Update()
    {
        if (lives <= 0)
        {
           EndGame();   //end game when spaceship runs out of lifes
        }
        scoreValue.GetComponent<TextMeshProUGUI>().text = score.ToString(); //update score
        if (score > highscore)
        {
            highscore = score;
            highScoreValue.GetComponent<TextMeshProUGUI>().text = highscore.ToString(); //update highscoew
        }
    }

    void StartButtonClicked()
    {
        numTimesClicked++;
        menue.SetActive(false); //turn off menu
        playing.SetActive(true);    //turn on playing state
        StartNewGame();
    }
    void StartNewGame()
    {
        startNextLevel(currentGameLevel);
        Debug.Log("num times clicked:" + numTimesClicked);
        if (numTimesClicked <= 1)
        {
            CreatePlayerSpaceship();    //only need to make the spaceship once
        }
    }

    void startNextLevel(int currentGameLevel)
    {
        currentGameLevel++;
        //Instansiate some number of asteroids depending on game level
        // create some asteroids near the edges of the screen
        for (int i = 0; i < currentGameLevel * 2 + 8; i++)
        {
            GameObject go = Instantiate(asteroidPrefab) as GameObject;
            float x, z;
            if (Random.Range(0f, 1f) < 0.5f)
                x = screenBottomLeft.x + Random.Range(0f, 0.15f) * screenWidth; // near the left edge
            else
                x = screenTopRight.x - Random.Range(0f, 0.15f) * screenWidth; // near the right edge
            if (Random.Range(0f, 1f) < 0.5f)
                z = screenBottomLeft.z + Random.Range(0f, 0.15f) * screenHeight; // near the bottom edge
            else
                z = screenTopRight.z - Random.Range(0f, 0.15f) * screenHeight; // near the top edge
            go.transform.position = new Vector3(x, 0f, z);
            asteroids.Add(go);  //add asteroids to list so we can destroy them all when game is over
        }
    }


    void CreatePlayerSpaceship()
    {
        //instantiates the player spaceship in the middle of the screen (x=0,y=0,z=0) - position set in inspector
        Instantiate(spaceShip);
        Debug.Log("Player spaceship instantiated");
    }
    void EndGame()
    {
        menue.SetActive(true);
        playing.SetActive(false);
        foreach(GameObject ast in asteroids)
        {
            Destroy(ast);
        }
        Debug.Log(asteroids);
        currentGameLevel = 0;   //reset game level
    }



}
