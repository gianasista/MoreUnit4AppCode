import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.PsiShortNamesCache;
import org.moreunit.JumpLogic;

/**
 * Created with IntelliJ IDEA.
 * User: vera
 * Date: 21.09.12
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
public class JumpAction extends AnAction
{
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
        PsiFile file = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());

        JumpLogic jumper = new JumpLogic(file);
        VirtualFile target = jumper.target();
        if (target != null)
            FileEditorManager.getInstance(project).openFile(target, true);
    }
}
