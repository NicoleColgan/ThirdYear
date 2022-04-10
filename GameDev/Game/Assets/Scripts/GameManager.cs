using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using TMPro;

public class GameManager : MonoBehaviour
{
    private int score,highScore;
    public Text scoreText,coinsText,highScoreText;
    public Player player; //reference to Player script
    //get references to UI Objects
    public GameObject button, gameOverText, alreadyPurchased, insufficientFunds, shopBtn, gameBtn, successfullPurchase;
    public GameObject[] birdPanels;
    public GameObject[] purchasedBirds;
    public Sprite[] yellowBirdSprites, greyBirdSprites, blueBirdSprites, pinkBirdSprites;
    private int price1 = 50, price2 = 25, price3 = 10;
    private void Start()
    {
        highScore = score;
        coinsText.text = StaticVars.coinsVal.ToString();

        //initialise UI elements
        gameOverText.SetActive(false);
        purchasedBirds[0].SetActive(true);
        //dont display shop material yet
        for (int i = 0; i < birdPanels.Length; i++)
        {
            birdPanels[i].SetActive(false);
            purchasedBirds[i + 1].SetActive(false);
        }
        alreadyPurchased.SetActive(false);
        insufficientFunds.SetActive(false);
        successfullPurchase.SetActive(false);
        gameBtn.SetActive(false);
    }

    private void Update()
    {
        coinsText.text = StaticVars.coinsVal.ToString();    //keep updating coin value
        if (score > highScore)  //update high score where necessary
        {
            highScore = score;
            highScoreText.text = highScore.ToString();
        }
    }

    private void Awake()
    {
        pause();    //dont start anything until user presses the play button
    }
    

    public void play()
    {
        score = 0;
        
        scoreText.text = score.ToString();  //update UI elements
        button.SetActive(false);
        gameOverText.SetActive(false);

        Time.timeScale = 1f;    //start things moving again
        player.enabled = true;

        GameObject[] pipes = GameObject.FindGameObjectsWithTag("Pipes");
        for (int i=0; i< pipes.Length; i++)
        {
            Destroy(pipes[i]);
            
        }
        GameObject[] coins = GameObject.FindGameObjectsWithTag("Coins");
        for (int i = 0; i < coins.Length; i++)
        {
            Destroy(coins[i]);

        }

    }

    private void pause()
    {
        
        Time.timeScale = 0f;    //dont let anything happen until button is pressed. (no updates on any scripts will be run)
        player.enabled = false;     //dont let player script do anything yet
    }

    //method for increasing score
    public void IncreaseScore()
    {
        score++;
        scoreText.text = score.ToString();
    }

    //method for increasing coins
    public void IncreaseCoins(int amm)
    {
        StaticVars.coinsVal += amm;
        coinsText.text = StaticVars.coinsVal.ToString();
    }

    //when the player dies
    public void GameOver()
    {
        gameOverText.SetActive(true);
        button.SetActive(true);
        pause();
    }

    //when shop button is pressed, this method is executed
    public void ShopGUI()
    {
        pause();    //in case they press shop during game 
        scoreText.enabled = false;  //change UI elements to be shop UI elements
        gameOverText.SetActive(false);
        button.SetActive(false);
        for (int i = 0; i < birdPanels.Length; i++)
        {
            birdPanels[i].SetActive(true);
        }
        alreadyPurchased.SetActive(false);
        insufficientFunds.SetActive(false);
        shopBtn.SetActive(false);
        gameBtn.SetActive(true);
    }

    //when game button is pressed, this method is executed
    public void GameGUI()
    {
        player.enabled = true;
        scoreText.enabled = true;
        button.SetActive(true);
        for (int i = 0; i < birdPanels.Length; i++)
        {
            birdPanels[i].SetActive(false);
        }
        shopBtn.SetActive(true);    //change to game UI elements
        gameBtn.SetActive(false);
        alreadyPurchased.SetActive(false);
        insufficientFunds.SetActive(false);
        successfullPurchase.SetActive(false);
    }
    
    //method to display purchased birds 
    public void PurchaseBird(int index)
    {
        if (index == 1)
        {
            purchasedBirds[1].SetActive(true);
        }
        else if (index == 2)
        {
            purchasedBirds[2].SetActive(true);
        }
        else if (index == 3)
        {
            purchasedBirds[3].SetActive(true);
        }
    }

    /**
     * Methods for handling purchasing birds
     * */
    public void Button1Clicked()
    {
        insufficientFunds.SetActive(false); //turn all messages off
        alreadyPurchased.SetActive(false);
        successfullPurchase.SetActive(false);

        //if they already purchased it (its black) tell them its already purchased
        if (birdPanels[0].GetComponent<Image>().color.Equals(Color.black))
        {
            alreadyPurchased.SetActive(true);
        }
        else if (int.Parse(coinsText.text) >= price1) //they can purchase the bird
        {
            //if its not already black (purchased) and they have sufficient funds to purchase, make it black to indicate that its been purchased
            if (!birdPanels[0].GetComponent<Image>().color.Equals(Color.black))
            {
                birdPanels[0].GetComponent<Image>().color = Color.black;
                //display bird in purchased birds
                PurchaseBird(1);
                //reduce coins by cost of bird
                StaticVars.coinsVal -= price1;
                //display successful purchase message
                successfullPurchase.SetActive(true);
            }

        }
        else
        {
            //insufficient funds to purchase it - display some text
            insufficientFunds.SetActive(true);
        }
    }
    public void Button2Clicked()
    {
        insufficientFunds.SetActive(false);
        alreadyPurchased.SetActive(false);
        successfullPurchase.SetActive(false);

        //if they already purchased it (its black) tell them its already purchased
        if (birdPanels[1].GetComponent<Image>().color.Equals(Color.black))
        {
            alreadyPurchased.SetActive(true);
        }
        else if (int.Parse(coinsText.text) >= price2) //they can purchase the bird
        {
            //if its not already black (purchased) and they have sufficient funds to purchase, make it black to indicate that its been purchased
            if (!birdPanels[1].GetComponent<Image>().color.Equals(Color.black))
            {
                birdPanels[1].GetComponent<Image>().color = Color.black;
                //display bird in purchased birds
                PurchaseBird(2);
                //reduce coins by cost of bird
                StaticVars.coinsVal -= price2;
                //display successful purchase message
                successfullPurchase.SetActive(true);
            }

        }
        else
        {
            //insufficient funds to purchase it - display some text
            insufficientFunds.SetActive(true);
        }
    }
    public void Button3Clicked()
    {
        insufficientFunds.SetActive(false);
        alreadyPurchased.SetActive(false);
        successfullPurchase.SetActive(false);

        //if they already purchased it (its black) tell them its already purchased
        if (birdPanels[2].GetComponent<Image>().color.Equals(Color.black))
        {
            alreadyPurchased.SetActive(true);
        }
        else if (int.Parse(coinsText.text) >= price3) //they can purchase the bird
        {
            //if its not already black (purchased) and they have sufficient funds to purchase, make it black to indicate that its been purchased
            if (!birdPanels[2].GetComponent<Image>().color.Equals(Color.black))
            {
                birdPanels[2].GetComponent<Image>().color = Color.black;
                //display bird in purchased birds
                PurchaseBird(3);
                //reduce coins by cost of bird
                StaticVars.coinsVal -= price3;
                //display successful purchase message
                successfullPurchase.SetActive(true);
            }

        }
        else
        {
            //insufficient funds to purchase it - display some text
            insufficientFunds.SetActive(true);
        }
    }

    //methods to handle switching bird
    public void ChangeToYellowBird()
    {
        player.setSprites(yellowBirdSprites,true);
    }
    public void ChangeToGreyBird()
    {
        player.setSprites(greyBirdSprites,false);
    }
    public void ChangeToBlueBird()
    {
        player.setSprites(blueBirdSprites,false);
    }
    public void ChangeToPinkBird()
    {
        player.setSprites(pinkBirdSprites,false);
    }


}
