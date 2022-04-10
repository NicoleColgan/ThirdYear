using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CoinSpawner : MonoBehaviour
{
    public GameObject YellowcoinPrefab, RedCoinPrefab;
    private int diffCoin = 0;
    public float spawnRate = 8f;
    public float minHeight = -0.5f;
    public float maxHeight = 0.5f;


    //these are unity events that are called automatically whenever the script in enabled or disabled
    private void OnEnable()    //at some point we wont want these to spawn anymore if the player dies
    {
        InvokeRepeating("Spawn", spawnRate, 1f);
    }

    private void OnDisable()    //dont spawn if player dies
    {
        CancelInvoke("Spawn");
    }


    public void Spawn()
    {
        diffCoin++;
        if (diffCoin !> 4)
        { //red coin
            GameObject Redcoin = Instantiate(RedCoinPrefab);   //indentity = no rotation
            Redcoin.transform.position += Vector3.up * Random.Range(minHeight, maxHeight);    //move up and down so not every set of pipes is the same
            diffCoin = 0;
        }
        else  //yellow coin
        {
            GameObject coin = Instantiate(YellowcoinPrefab);   //indentity = no rotation
            coin.transform.position += Vector3.up * Random.Range(minHeight, maxHeight);    //move up and down so not every set of pipes is the same
        }
    }
}
