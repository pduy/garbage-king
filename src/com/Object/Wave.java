package com.Object;

public class Wave {
    public int[] _monster;
    public int[] _time;
    public int[] _path;
    public int _curMonster;

    public Wave(int[] monster, int[] time, int[] path)
    {
        _monster = monster;
        _time = time;
        _path = path;

        _curMonster = 0;
    }

//    public int GenerateMonster() 
//    {
//        ++_curMonster;
//        return _curMonster - 1;
//    }

    public boolean IsEndOfWave()
    {
        if (_curMonster == _monster.length)
            return true;
            
        return false;
    }
}
