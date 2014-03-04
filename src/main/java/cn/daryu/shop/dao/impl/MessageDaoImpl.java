// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.MessageDao;
import net.shopxx.entity.Member;
import net.shopxx.entity.Message;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class MessageDaoImpl extends BaseDaoImpl
    implements MessageDao
{

    public MessageDaoImpl()
    {
    }

    public Page findPage(Member member, Pageable pageable)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Message);
        Root root = criteriaquery.from(net/shopxx/entity/Message);
        criteriaquery.select(root);
        Predicate predicate = criteriabuilder.conjunction();
        predicate = criteriabuilder.and(new Predicate[] {
            predicate, criteriabuilder.isNull(root.get("forMessage")), criteriabuilder.equal(root.get("isDraft"), Boolean.valueOf(false))
        });
        if(member != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.and(criteriabuilder.equal(root.get("sender"), member), criteriabuilder.equal(root.get("senderDelete"), Boolean.valueOf(false))), criteriabuilder.and(criteriabuilder.equal(root.get("receiver"), member), criteriabuilder.equal(root.get("receiverDelete"), Boolean.valueOf(false)))));
        else
            predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.and(criteriabuilder.isNull(root.get("sender")), criteriabuilder.equal(root.get("senderDelete"), Boolean.valueOf(false))), criteriabuilder.and(criteriabuilder.isNull(root.get("receiver")), criteriabuilder.equal(root.get("receiverDelete"), Boolean.valueOf(false)))));
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, pageable);
    }

    public Page findDraftPage(Member sender, Pageable pageable)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Message);
        Root root = criteriaquery.from(net/shopxx/entity/Message);
        criteriaquery.select(root);
        Predicate predicate = criteriabuilder.conjunction();
        predicate = criteriabuilder.and(new Predicate[] {
            predicate, criteriabuilder.isNull(root.get("forMessage")), criteriabuilder.equal(root.get("isDraft"), Boolean.valueOf(true))
        });
        if(sender != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("sender"), sender));
        else
            predicate = criteriabuilder.and(predicate, criteriabuilder.isNull(root.get("sender")));
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, pageable);
    }

    public Long count(Member member, Boolean read)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Message);
        Root root = criteriaquery.from(net/shopxx/entity/Message);
        criteriaquery.select(root);
        Predicate predicate = criteriabuilder.conjunction();
        predicate = criteriabuilder.and(new Predicate[] {
            predicate, criteriabuilder.isNull(root.get("forMessage")), criteriabuilder.equal(root.get("isDraft"), Boolean.valueOf(false))
        });
        if(member != null)
        {
            if(read != null)
                predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.and(new Predicate[] {
                    criteriabuilder.equal(root.get("sender"), member), criteriabuilder.equal(root.get("senderDelete"), Boolean.valueOf(false)), criteriabuilder.equal(root.get("senderRead"), read)
                }), criteriabuilder.and(new Predicate[] {
                    criteriabuilder.equal(root.get("receiver"), member), criteriabuilder.equal(root.get("receiverDelete"), Boolean.valueOf(false)), criteriabuilder.equal(root.get("receiverRead"), read)
                })));
            else
                predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.and(criteriabuilder.equal(root.get("sender"), member), criteriabuilder.equal(root.get("senderDelete"), Boolean.valueOf(false))), criteriabuilder.and(criteriabuilder.equal(root.get("receiver"), member), criteriabuilder.equal(root.get("receiverDelete"), Boolean.valueOf(false)))));
        } else
        if(read != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.and(new Predicate[] {
                criteriabuilder.isNull(root.get("sender")), criteriabuilder.equal(root.get("senderDelete"), Boolean.valueOf(false)), criteriabuilder.equal(root.get("senderRead"), read)
            }), criteriabuilder.and(new Predicate[] {
                criteriabuilder.isNull(root.get("receiver")), criteriabuilder.equal(root.get("receiverDelete"), Boolean.valueOf(false)), criteriabuilder.equal(root.get("receiverRead"), read)
            })));
        else
            predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.and(criteriabuilder.isNull(root.get("sender")), criteriabuilder.equal(root.get("senderDelete"), Boolean.valueOf(false))), criteriabuilder.and(criteriabuilder.isNull(root.get("receiver")), criteriabuilder.equal(root.get("receiverDelete"), Boolean.valueOf(false)))));
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, null);
    }

    public void remove(Long id, Member member)
    {
        Message message = (Message)super.find(id);
        if(message == null || message.getForMessage() != null)
            return;
        if(member == message.getReceiver())
        {
            if(!message.getIsDraft().booleanValue())
                if(message.getSenderDelete().booleanValue())
                {
                    super.remove(message);
                } else
                {
                    message.setReceiverDelete(Boolean.valueOf(true));
                    super.merge(message);
                }
        } else
        if(member == message.getSender())
            if(message.getIsDraft().booleanValue())
                super.remove(message);
            else
            if(message.getReceiverDelete().booleanValue())
            {
                super.remove(message);
            } else
            {
                message.setSenderDelete(Boolean.valueOf(true));
                super.merge(message);
            }
    }
}
