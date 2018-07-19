package video.ysh.com.supervideo;


import java.util.HashMap;

import video.ysh.com.supervideo.fragment.BaseFragment;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/17 下午5:38
 * - @Email whynightcode@gmail.com
 */
public class FragmentMagerWarper {

    private FragmentMagerWarper() {
    }

    public static FragmentMagerWarper newInstance() {
        return Bulid.sFragmentMagerWarper;
    }

    private static class Bulid {
        private static FragmentMagerWarper sFragmentMagerWarper = new FragmentMagerWarper();
    }

    private HashMap<String, BaseFragment> mHashMap = new HashMap<>();

    public BaseFragment createFragment(Class<?> clazz, boolean isobtain) {
        BaseFragment resultFragment = null;
        String name = clazz.getName();
        if (!mHashMap.containsKey(name)) {
            try {
                resultFragment = (BaseFragment) Class.forName(name).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (isobtain) {
            mHashMap.put(name, resultFragment);
        }
        return resultFragment;
    }
}
