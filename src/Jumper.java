import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.GlobalSearchScope;

/**
 * Created with IntelliJ IDEA.
 * User: vera
 * Date: 23.09.12
 * Time: 20:47
 * To change this template use File | Settings | File Templates.
 */
public class Jumper
{
    private PsiFile sourceFile;
    private String fileExtension;

    private static final String TEST_CASE_SUFFIX = "Test";

    public Jumper(PsiFile source)
    {
        this.sourceFile = source;
    }

    public VirtualFile getTarget()
    {
        String target;
        String fileNameWithoutExtension = getFileNameWithoutExtention();
        if(isTestCase(fileNameWithoutExtension))
            target = getNameOfCut(fileNameWithoutExtension);
        else
            target = getNameOfTest(fileNameWithoutExtension);

        VirtualFile[] contentRoots = ProjectRootManager.getInstance(sourceFile.getProject()).getContentRoots();
        for(VirtualFile contentRoot : contentRoots)
        {
            VirtualFile result = findFileByNameInVirtualFile(target, contentRoot);
            if(result != null)
                return result;
        }

        return null;
    }

    private VirtualFile findFileByNameInVirtualFile(String filename, VirtualFile virtuaFile)
    {
        VirtualFile result = virtuaFile.findChild(filename);
        if (result != null)
            return result;

        VirtualFile[] files = virtuaFile.getChildren();
        for (VirtualFile child : files)
        {
            result = findFileByNameInVirtualFile(filename, child);
            if (result != null)
            {
                return result;
            }
        }
        return null;
    }

    private String getFileNameWithoutExtention()
    {
        String filename = sourceFile.getName();
        int index = filename.lastIndexOf('.');
        this.fileExtension = filename.substring(index);
        return filename.substring(0, index);
    }

    private boolean isTestCase(String classname)
    {
        return classname.endsWith(TEST_CASE_SUFFIX);
    }

    private String getNameOfCut(String testcase)
    {
        return testcase.replaceFirst(TEST_CASE_SUFFIX, "")+this.fileExtension;
    }

    private String getNameOfTest(String classundertest)
    {
        return classundertest+TEST_CASE_SUFFIX+this.fileExtension;
    }
}
