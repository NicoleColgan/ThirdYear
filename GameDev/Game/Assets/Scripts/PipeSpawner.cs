using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PipeSpawner : MonoBehaviour
{
    public GameObject pipesPrefab;
    public float spawnRate = 2f;
    public float minHeight = -1f;
    public float maxHeight = 1f;

    //these are unity events that are called automatically whenever the script in enabled or disabled
    private void OnEnable()    //at some point we wont want these to spawn anymore if the player dies
    {
        InvokeRepeating("Spawn", 1f, spawnRate);
    }

    private void OnDisable()
    {
        CancelInvoke("Spawn");
    }

    public void Spawn()
    {
        GameObject pipes = Instantiate(pipesPrefab, transform.position, Quaternion.identity);   //indentity = no rotation
        pipes.transform.position += Vector3.up * Random.Range(minHeight, maxHeight);    //move up and down so not every set of pipes is the same
    }
}
